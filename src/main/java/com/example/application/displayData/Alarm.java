package com.example.application.displayData;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Alarm {

    public Label createAlarmLabel(String machine, int machineOutput, int leakTesterOutput)
    {
        double efficiency = ((double) leakTesterOutput / (double) machineOutput);
        Label label = new Label();

        if(leakTesterOutput > machineOutput) {
            label.setText("The output count is greater than what has been produced, check the count sensors are working correctly.");
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
}
