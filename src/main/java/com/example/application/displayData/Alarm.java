package com.example.application.displayData;

import com.example.application.repositories.CounterRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Alarm {

    CounterRepository counterRepository;

    public Alarm(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    /**
     * createAlarmLabel() returns a label to the drawGraph() method in the ProductionView.class.
     * The method first checks if there is a problem with the counting system, if okay. The efficiency percentage
     * is calculated and the relevant warning message is set to the label.setText()
     *
     * @param machine - The machine name that will be displayed on the label for example "Uniloy 1".
     * @param machineOutput - Number of bottles the machine has produced. Retrieved from the database.
     * @param leakTesterOutput - Number of bottles the leaktester has passed. Retrieved from the database.
     * @return label - The returned label to be displayed on the counts page.
     */
    public Label createAlarmLabel(String machine, int machineOutput, int leakTesterOutput)
    {
        double efficiency = ((double) leakTesterOutput / (double) machineOutput);
        Label label = new Label();

        if(leakTesterOutput > machineOutput) {
            label.setText("The output count is greater than what has been produced, " +
                    "check the count sensors are working correctly.");
            label.getStyle().set("color","red");
            label.getStyle().set("fontWeight", "bold");
        }
        else if(efficiency < 0.98 && efficiency > 0.9) {
            label.setText("***low efficiency, " + machine + " has lost >2% of total bottles produced***");
            label.getStyle().set("color","red");
            label.getStyle().set("fontWeight", "bold");
        }
        else if(efficiency < 0.9) {
            label.setText("***VERY LOW EFFICIENCY, " + machine + " has lost >10% of total bottles produced***");
            label.getStyle().set("color","red");
            label.getStyle().set("fontWeight", "bold");
        }

        return label;
    }

    /**
     * createDialog() returns a Dialog to the drawGraph() method in the ProductionView.class.
     * The method determines which machine either the trimmer or the leaktester has the highest number
     * of bottles rejected. That machine is then displayed in the H2 and the Paragraph.
     *
     * @param trimmerIn - Number of bottles the trimmer has received. Retrieved from the database.
     * @param trimmerOut - Number of bottles the trimmer has successfully trimmed. Retrieved from the database.
     * @param leaktesterIn - Number of bottles the leaktester has received. Retrieved from the database.
     * @param leaktesterOut - Number of bottles the leaktester has passed. Retrieved from the database.
     * @return dialog - A Vaadin dialog component with a H2 headline and Paragraph
     */
    public Dialog createDialog(int trimmerIn, int trimmerOut, int leaktesterIn, int leaktesterOut)
    {
        String machine;
        int rejects;

        if((trimmerIn - trimmerOut) > (leaktesterIn - leaktesterOut)) {
            machine = "Trimmer";
            rejects = trimmerIn - trimmerOut;
        }
        else {
            machine = "Leaktester";
            rejects = leaktesterIn - leaktesterOut;
        }

        Dialog dialog = new Dialog();
        dialog.getElement()
                .setAttribute("aria-label", "Machine with highest number of rejects");

        H2 headline = new H2("The " + machine + " has lost " + rejects + " bottles");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        Paragraph paragraph = new Paragraph("Check the input and output count " +
                "sensors are detecting bottles. " +
                "Check the " + machine + " is operating correctly. " +
                "Call for Engineering assistance if unable to resolve.");

        Button closeButton = new Button("Close");
        closeButton.addClickListener(e -> dialog.close());

        VerticalLayout dialogLayout = new VerticalLayout(headline, paragraph,
                closeButton);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");
        dialogLayout.setAlignSelf(FlexComponent.Alignment.END, closeButton);

        dialog.add(dialogLayout);
        dialog.open();

        return dialog;
    }

    /**
     * alarmService creates a VeticalLayout and attaches a label from the createAlarmLabel() method,
     * and create a dialog box from the createDialog() method within the Alarm class. This is then returned to
     * ProductionView class to be displayed below the chart.
     *
     * @param machine - Selects the data from the database of the choosen machine.
     * @return verticalLayout - returns to the ProductionView class
     */
    public VerticalLayout alarmService(String machine)
    {
        VerticalLayout verticalLayout = new VerticalLayout();

        Button alert = new Button("Details");
        alert.addThemeVariants(ButtonVariant.LUMO_ERROR);

        switch (machine) {
            case "Uniloy 1":
                verticalLayout.add(createAlarmLabel(machine,
                        counterRepository.getCurrentMachineOut(),
                        counterRepository.getCurrentLeaktesterOut()));
                alert.addClickListener(buttonClickEvent -> createDialog(counterRepository.getCurrentTrimmerIn(),
                        counterRepository.getCurrentTrimmerOut(), counterRepository.getCurrentLeaktesterIn(),
                        counterRepository.getCurrentLeaktesterOut()));
                break;
            case "Uniloy 2":
                verticalLayout.add(createAlarmLabel(machine,
                        counterRepository.getU2CurrentMachineOut(),
                        counterRepository.getU2CurrentLeaktesterOut()));
                alert.addClickListener(buttonClickEvent -> createDialog(counterRepository.getU2CurrentTrimmerIn(),
                        counterRepository.getU2CurrentTrimmerOut(), counterRepository.getU2CurrentLeaktesterIn(),
                        counterRepository.getU2CurrentLeaktesterOut()));
                break;
            case "Uniloy 3":
                verticalLayout.add(createAlarmLabel(machine,
                        counterRepository.getU3CurrentMachineOut(),
                        counterRepository.getU3CurrentLeaktesterOut()));
                alert.addClickListener(buttonClickEvent -> createDialog(counterRepository.getU3CurrentTrimmerIn(),
                        counterRepository.getU3CurrentTrimmerOut(), counterRepository.getU3CurrentLeaktesterIn(),
                        counterRepository.getU3CurrentLeaktesterOut()));
                break;
            default:
                System.out.println("Error");
        }

        verticalLayout.add(alert);
        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        return verticalLayout;
    }
}
