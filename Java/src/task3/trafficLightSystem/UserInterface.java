package task3.trafficLightSystem;

import org.javatuples.Quartet;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static task3.trafficLightSystem.Constant.directions;

public class UserInterface extends Thread {

    @Override
    public void run() {
        generateGui();
    }

    // build complete interface for giving input and showing output
    public static void generateGui() {
        JFrame frame = new JFrame(); //creating instance of JFrame
        frame.setTitle("Programming Lab Assignment 1");

        // label created for few objects
        JLabel sourceDirection = new JLabel("Source Direction");
        JLabel destinationDirection = new JLabel("Destination Direction");
        JLabel carArrivalTime = new JLabel("Car Arrival Time");
        JLabel numCar = new JLabel("No. Car Arriving");

        // button created for adding car and opening status window
        JButton addButton = new JButton("Add More Cars");
        JButton statusButton = new JButton("Status Button");

        // spinner for giving car arrival time and number of car arriving
        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(1, 1, 10000, 1);
        SpinnerModel spinnerNumberModelCars = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner carTime = new JSpinner(spinnerNumberModel);
        JSpinner carNum = new JSpinner(spinnerNumberModelCars);

        // source and destination direction list created
        directions.add("South");
        directions.add("West");
        directions.add("East");
        JList sourceDirectionList = new JList(directions.toArray());
        JList destinationDirectionList = new JList(directions.toArray());
        sourceDirectionList.setSelectedIndex(0);
        destinationDirectionList.setSelectedIndex(2);

        // vehicle status table created
        DefaultTableModel outputTable = new DefaultTableModel(new String[]{"Vehicle Number", "Status"}, 0);
        JTable outputJTable = new JTable(outputTable);
        JScrollPane outputTableScrollPane = new JScrollPane(outputJTable);

        // current program time status table created
        DefaultTableModel TimeTable = new DefaultTableModel(new String[]{"Time", "Status"}, 0);
        JTable timeJTable = new JTable(TimeTable);
        JScrollPane timeTableScrollPane = new JScrollPane(timeJTable);

        // x axis, y axis, width, height for all object created above are given
        timeTableScrollPane.setBounds(80,20,400,40);
        sourceDirection.setBounds(50, 90, 250, 30);
        sourceDirectionList.setBounds(350, 90, 100, 130);
        destinationDirection.setBounds(50, 240, 250, 30);
        destinationDirectionList.setBounds(350, 240, 100, 130);
        carArrivalTime.setBounds(50, 400, 250, 30);
        carTime.setBounds(350, 400, 100, 30);
        numCar.setBounds(50, 460, 250, 30);
        carNum.setBounds(350, 460, 100, 30);
        addButton.setBounds(200, 530, 200, 40);
        outputTableScrollPane.setBounds(0, 600, 600, 140);
        statusButton.setBounds(200, 770, 200, 40);

        // Action Listener when add button is clicked
        addButton.addActionListener(actionEvent -> {
            String item1 = directions.get(sourceDirectionList.getSelectedIndex());      // get source direction
            String item2 = directions.get(destinationDirectionList.getSelectedIndex());  // get destination direction
            Integer arrivalTime = (Integer) carTime.getValue();     // get car arrival time
            Integer numCarArriving = (Integer) carNum.getValue();       // get number of car arriving
            if (arrivalTime > (Constant.programTime - Constant.startTime)) {
                // add all vehicle in list
                for (int i = 0; i < numCarArriving; i++) {
                    Constant.allUser.add(new Quartet<>(++Constant.vehicleNumber,item1,item2,arrivalTime));
                }
            }
        });

        // Action Listener for updating current time and vehicle status in GUI
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                // vehicle status for each vehicle updated
                outputTable.setRowCount(0);
                for (Quartet<Integer, String, String, Integer> user : Constant.userDetails)
                {
                    if ( user.getValue3() < (Constant.programTime - Constant.startTime)) {
                        outputTable.addRow(new Object[]{user.getValue0(), Constant.vehicleStatus.get(user.getValue0() - 1)});
                    }
                }

                // current program time updated
                TimeTable.setRowCount(0);
                TimeTable.addRow(new Object[]{"Current Time" , (Constant.programTime - Constant.startTime)});

            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);       // timer of 0.1sec set to start action listener
        timer.setRepeats(true);     // timer is repeat status set true
        timer.start();

        statusButton.addActionListener(actionEvent -> generateStatusGui());     // give status GUI on status button mouse click
        statusButton.setEnabled(true);

        // all objected added to frame
        frame.add(timeTableScrollPane);
        frame.add(sourceDirection);
        frame.add(sourceDirectionList);
        frame.add(destinationDirection);
        frame.add(destinationDirectionList);
        frame.add(carArrivalTime);
        frame.add(carTime);
        frame.add(numCar);
        frame.add(carNum);
        frame.add(addButton);
        frame.add(outputTableScrollPane);
        frame.add(statusButton);

        frame.setSize(600, 900);//600 width and 1000 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
    }

    public static void generateStatusGui() {
        JFrame frame = new JFrame(); //creating instance of JFrame
        frame.setTitle("Traffic Light System Status");

        // panel for traffic light GUI
        JPanel panel = new JPanel();
        TrafficLightGui trafficLightGui = new TrafficLightGui();
        trafficLightGui.TrafficLightGui(panel);

        // panel for traffic light and vehicle status
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p3 = new JPanel(new BorderLayout());

        // table for traffic light and vehicle status
        DefaultTableModel trafficLightDetails = new DefaultTableModel(new String[]{"Traffic Light", "Status", "Time"}, 0);
        DefaultTableModel outputStatusTable = new DefaultTableModel(new String[]{"Vehicle", "Source", "Destination","Status", "Remaining Time"}, 0);
        JTable trafficLightTable = new JTable(trafficLightDetails);
        JTable statusTable = new JTable(outputStatusTable);
        JScrollPane trafficLightScrollPane = new JScrollPane(trafficLightTable);
        JScrollPane outputStatusScrollPane = new JScrollPane(statusTable);

        // tab pane created for traffic light and vehicle status
        JTabbedPane tp = new JTabbedPane();

        // Action Listener for updating traffic light status and vehicle status in GUI
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshAction( trafficLightDetails, outputStatusTable);            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);       // timer of 0.1sec set to start action listener
        timer.setRepeats(true);     // timer is repeat status set true
        timer.start();

        // frame size and layout set
        frame.setLayout(new BorderLayout());
        frame.setSize(1000,900);


        // traffic light and vehicle status table added to panel
        p2.add(trafficLightScrollPane, BorderLayout.CENTER);
        p3.add(outputStatusScrollPane, BorderLayout.CENTER);

        // traffic light and vehicle status panel added to tab
        tp.add("Traffic Light Status", p2);
        tp.add("Vehicle Status", p3);

        // frame layout setting
        frame.add(panel,BorderLayout.PAGE_START);
        frame.add(tp, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    private static void refreshAction(DefaultTableModel trafficLightDetails, DefaultTableModel outputStatusDetails) {
        // traffic light status updated
        trafficLightDetails.setRowCount(0);
        if (Constant.greenTrafficlight==1){
            trafficLightDetails.addRow(new Object[]{"T1", "Green", Constant.programTime - Constant.startTrafficLightTime});
            trafficLightDetails.addRow(new Object[]{"T2", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T3", "Red", "--"});
        } else if (Constant.greenTrafficlight==2){
            trafficLightDetails.addRow(new Object[]{"T1", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T2", "Green", Constant.programTime - Constant.startTrafficLightTime});
            trafficLightDetails.addRow(new Object[]{"T3", "Red", "--"});
        } else if (Constant.greenTrafficlight==3){
            trafficLightDetails.addRow(new Object[]{"T1", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T2", "Red", "--"});
            trafficLightDetails.addRow(new Object[]{"T3", "Green", Constant.programTime - Constant.startTrafficLightTime});
        }

        // vehicle status updated
        outputStatusDetails.setRowCount(0);
        for (Quartet<Integer, String, String,Integer> user : Constant.userDetails)
        {
            if ( user.getValue3() < (Constant.programTime - Constant.startTime)) {
                outputStatusDetails.addRow(new Object[]{user.getValue0(), user.getValue1(), user.getValue2(), Constant.vehicleStatus.get(user.getValue0() - 1), Constant.vehicleTimeStatus.get(user.getValue0() - 1)});
            }
        }
    }
}
