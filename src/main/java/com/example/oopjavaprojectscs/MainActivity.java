package com.example.oopjavaprojectscs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GameManager gm;
    private View currentView;

    private Mission currentMission;
    private List<CrewMember> selectedCrew = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gm = GameManager.getInstance();
        gm.initializeDefaultCrew();

        showHomeScreen();
    }

    private void setContent(View view) {
        setContentView(view);
        currentView = view;
    }


    private void showHomeScreen() {
        View v = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        setContent(v);

        Button btnRecruit = v.findViewById(R.id.btnRecruitCrew);
        Button btnQuarters = v.findViewById(R.id.btnQuarters);
        Button btnSimulator = v.findViewById(R.id.btnSimulator);
        Button btnMission = v.findViewById(R.id.btnMissionControl);
        Button btnStats = v.findViewById(R.id.btnStatistics);

        btnRecruit.setOnClickListener(view -> showRecruitCrewView());
        btnQuarters.setOnClickListener(view -> showQuartersView());
        btnSimulator.setOnClickListener(view -> showSimulatorView());
        btnMission.setOnClickListener(view -> showMissionControlView());
        btnStats.setOnClickListener(view -> showStatisticsView());
    }


    private void showRecruitCrewView() {
        View v = LayoutInflater.from(this).inflate(R.layout.view_recruit_crew, null);
        setContent(v);

        EditText inputName = v.findViewById(R.id.inputName);
        Spinner spinnerSpec = v.findViewById(R.id.spinnerSpecialization);
        Button btnCreate = v.findViewById(R.id.btnCreateCrew);
        Button btnBack = v.findViewById(R.id.btnBackHome1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Pilot", "Engineer", "Medic", "Scientist", "Soldier"}
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpec.setAdapter(adapter);

        btnCreate.setOnClickListener(view -> {
            String name = inputName.getText().toString().trim();
            String spec = (String) spinnerSpec.getSelectedItem();

            if (name.isEmpty()) {
                Toast.makeText(this, "Enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            CrewMember member;

            switch (spec) {
                case "Pilot": member = new Pilot(0, name); break;
                case "Engineer": member = new Engineer(0, name); break;
                case "Medic": member = new Medic(0, name); break;
                case "Scientist": member = new Scientist(0, name); break;
                default: member = new Soldier(0, name); break;
            }

            gm.storage.addCrewMember(member);

            Toast.makeText(this, "Crew member created", Toast.LENGTH_SHORT).show();
            inputName.setText("");
        });

        btnBack.setOnClickListener(view -> showHomeScreen());
    }


    private void showQuartersView() {
        View v = LayoutInflater.from(this).inflate(R.layout.view_quarters, null);
        setContent(v);

        TextView txtList = v.findViewById(R.id.txtQuartersList);
        Button btnMoveToMission = v.findViewById(R.id.btnMoveToMission);
        Button btnBack = v.findViewById(R.id.btnBackHome2);

        StringBuilder sb = new StringBuilder();

        for (CrewMember m : gm.storage.getAllCrew()) {

            sb.append(m.getName())
                    .append(" ")
                    .append(m.getOccupation())
                    .append(" (HP: ").append(m.getHp())
                    .append(", XP: ").append(m.getExperience())
                    .append(")\n");

            // safe restore only if actually in quarters
            if (m.getLocation() == Location.QUARTERS) {
                gm.quarters.enter(m);
            }
        }

        txtList.setText(sb.toString());
        btnMoveToMission.setOnClickListener(view -> showMissionControlView());
        btnBack.setOnClickListener(view -> showHomeScreen());
    }


    private void showSimulatorView() {
        View v = LayoutInflater.from(this).inflate(R.layout.view_simulator, null);
        setContent(v);

        TextView txtTraining = v.findViewById(R.id.txtTrainingList);
        Button btnTrainAll = v.findViewById(R.id.btnTrainAll);
        Button btnBack = v.findViewById(R.id.btnBackHome3);

        updateTrainingList(txtTraining);

        btnTrainAll.setOnClickListener(view -> {

            List<CrewMember> crew = gm.storage.getAllCrew();

            gm.simulator.trainAll(crew);

            for (CrewMember m : crew) {
                gm.statistics.recordTraining(m);
            }

            updateTrainingList(txtTraining);
        });

        btnBack.setOnClickListener(view -> showHomeScreen());
    }

    private void updateTrainingList(TextView txt) {

        StringBuilder sb = new StringBuilder();

        for (CrewMember m : gm.storage.getAllCrew()) {
            sb.append(m.getName())
                    .append(" ")
                    .append(m.getOccupation())
                    .append(" (XP: ").append(m.getExperience())
                    .append(", Trainings: ").append(m.getTrainings())
                    .append(")\n");
        }

        txt.setText(sb.toString());
    }


    private void showMissionControlView() {
        View v = LayoutInflater.from(this).inflate(R.layout.view_mission_control, null);
        setContent(v);

        Button btnStart = v.findViewById(R.id.btnStartMission);
        Button btnNext = v.findViewById(R.id.btnNextTurn);
        TextView txtLog = v.findViewById(R.id.txtMissionLog);
        Button btnBack = v.findViewById(R.id.btnBackHome4);

        btnStart.setOnClickListener(view -> {

            List<CrewMember> crew = gm.storage.getAllCrew();

            selectedCrew.clear();

            for (CrewMember m : crew) {
                if (m.isAlive()) {
                    selectedCrew.add(m);
                }
                if (selectedCrew.size() == 2) break;
            }

            if (selectedCrew.size() < 2) {
                txtLog.setText("Not enough alive crew for a mission.\n");
                return;
            }

            currentMission = gm.missionControl.createMission(
                    selectedCrew.get(0),
                    selectedCrew.get(1)
            );

            if (currentMission == null) {
                txtLog.setText("Failed to create mission.\n");
                return;
            }

            gm.missionControl.launchMission();
            txtLog.setText("Mission started.\n");
        });

        btnNext.setOnClickListener(view -> {

            if (currentMission == null || !currentMission.isActive()) {
                txtLog.append("No active mission.\n");
                return;
            }

            String log = currentMission.executeTurn();
            txtLog.append(log + "\n");
        });

        btnBack.setOnClickListener(view -> showHomeScreen());
    }

    private void showStatisticsView() {
        View v = LayoutInflater.from(this).inflate(R.layout.view_statistics, null);
        setContent(v);

        TextView txtStats = v.findViewById(R.id.txtStats);
        Button btnBack = v.findViewById(R.id.btnBackHome5);

        StringBuilder sb = new StringBuilder();

        for (CrewMember m : gm.storage.getAllCrew()) {
            sb.append(m.getName())
                    .append(" | Missions: ").append(m.getMissionsCompleted())
                    .append(" | Wins: ").append(m.getWins())
                    .append(" | Trainings: ").append(m.getTrainings())
                    .append("\n");
        }

        txtStats.setText(sb.toString());

        btnBack.setOnClickListener(view -> showHomeScreen());
    }
}