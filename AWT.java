package LAB6;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Person {
    private String name;
    private String gender;
    private String jobDescription;
    private int experience;
    private String location;

    public Person(String name, String gender, String jobDescription, int experience, String location) {
        this.name = name;
        this.gender = gender;
        this.jobDescription = jobDescription;
        this.experience = experience;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public int getExperience() {
        return experience;
    }

    public String getLocation() {
        return location;
    }
}

class ShowAllFrame extends Frame {
    private TextArea showAllTextArea;

    ShowAllFrame(ArrayList<Person> peopleList) {
        showAllTextArea = new TextArea();
        showAllTextArea.setEditable(false);

        for (Person person : peopleList) {
            showAllTextArea.append("Name: " + person.getName() + "\n");
            showAllTextArea.append("Gender: " + person.getGender() + "\n");
            showAllTextArea.append("Job Description: " + person.getJobDescription() + "\n");
            showAllTextArea.append("Experience (in years): " + person.getExperience() + " years\n");
            showAllTextArea.append("Location: " + person.getLocation() + "\n");
            showAllTextArea.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }

        add(showAllTextArea);

        setSize(600, 500);
        setTitle("Show All People");
        setLayout(new FlowLayout());
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

public class AWT extends Frame {
    private ArrayList<Person> peopleList = new ArrayList<>();
    private TextField nameTextField;
    private CheckboxGroup genderGroup;
    private TextArea jobTextArea;
    private TextField experienceTextField;
    private Choice locationChoice;

    AWT() {
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();

        Label genderLabel = new Label("Gender:");
        CheckboxGroup genderGroup = new CheckboxGroup();
        Checkbox maleCheckbox = new Checkbox("Male", genderGroup, false);
        Checkbox femaleCheckbox = new Checkbox("Female", genderGroup, false);

        Label jobLabel = new Label("Job Description:");
        TextArea jobTextArea = new TextArea();

        Label experienceLabel = new Label("Experience (in years):");
        Scrollbar experienceScrollbar = new Scrollbar(Scrollbar.HORIZONTAL);
        TextField experienceTextField = new TextField();

        Label locationLabel = new Label("Location:");
        Choice locationChoice = new Choice();

        Button addButton = new Button("Add");
        Button saveAllButton = new Button("Save All");

        locationChoice.add("Select");
        locationChoice.add("Ramnicu Sarat");
        locationChoice.add("Buzau");
        locationChoice.add("Chitila");
        locationChoice.add("Rahova");
        locationChoice.add("Vitan");

        nameLabel.setBounds(30, 50, 80, 20);
        nameTextField.setBounds(120, 50, 150, 20);

        genderLabel.setBounds(30, 80, 80, 20);
        maleCheckbox.setBounds(120, 80, 50, 20);
        femaleCheckbox.setBounds(180, 80, 60, 20);

        jobLabel.setBounds(30, 120, 150, 20);
        jobTextArea.setBounds(30, 150, 300, 100);

        experienceLabel.setBounds(30, 270, 150, 20);
        experienceTextField.setBounds(180, 270, 30, 20);
        experienceScrollbar.setBounds(220, 270, 30, 20);

        locationLabel.setBounds(30, 300, 80, 20);
        locationChoice.setBounds(120, 300, 100, 20);

        addButton.setBounds(30, 350, 80, 30);

        saveAllButton.setBounds(310, 350, 80, 30);

        add(saveAllButton);
        add(nameLabel);
        add(nameTextField);

        add(genderLabel);
        add(maleCheckbox);
        add(femaleCheckbox);

        add(jobLabel);
        add(jobTextArea);

        add(experienceLabel);
        add(experienceTextField);
        add(experienceScrollbar);

        add(locationLabel);
        add(locationChoice);

        add(addButton);


        setSize(500, 400);
        setLayout(null);
        setVisible(true);


        experienceScrollbar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                experienceTextField.setText(String.valueOf(e.getValue()));
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String gender = maleCheckbox.getState() ? "Male" : "Female";
                String jobDescription = jobTextArea.getText();
                int experience = Integer.parseInt(experienceTextField.getText());
                String location = locationChoice.getSelectedItem();

                Person person = new Person(name, gender, jobDescription, experience, location);
                peopleList.add(person);

                nameTextField.setText("");
                maleCheckbox.setState(false);
                femaleCheckbox.setState(false);
                jobTextArea.setText("");
                experienceTextField.setText("");
                locationChoice.select(0);
            }
        });

        Button showAllButton = new Button("Show All");
        showAllButton.setBounds(220, 350, 80, 30);
        add(showAllButton);

        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ShowAllFrame(peopleList);
            }
        });

        saveAllButton.addActionListener(new ActionListener() {
            String fileName = "/home/mihai/Desktop/people.txt";
            public void actionPerformed(ActionEvent e) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                    for (Person person : peopleList) {
                        writer.println("Name: " + person.getName());
                        writer.println("Gender: " + person.getGender());
                        writer.println("Job Description: " + person.getJobDescription());
                        writer.println("Experience (in years): " + person.getExperience() + " years");
                        writer.println("Location: " + person.getLocation());
                        writer.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    System.out.println("Data saved to " + fileName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public static void main(String args[]) {
        AWT f = new AWT();
    }
}