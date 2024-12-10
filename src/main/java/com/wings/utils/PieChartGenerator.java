package com.wings.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class PieChartGenerator {

    public static void main(String[] args) {
        // Define variables for pie chart data
        String[] categories = {"Failed", "Skipped", "Passed"};
        double[] values = {17, 13, 170};

        // Define HTML color codes for each category
        String[] htmlColors = {"#FF5733", "#FFC300", "#39A544"};

        // Convert HTML color codes to Color objects
        Color[] colors = new Color[htmlColors.length];
        for (int i = 0; i < htmlColors.length; i++) {
            colors[i] = Color.decode(htmlColors[i]);
        }

        // Generate dataset for pie chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < categories.length; i++) {
            dataset.setValue(categories[i], values[i]);
        }

        // Create the pie chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Wings Test Execution",  // Chart title
                dataset,             // Dataset
                true,                // Include legend
                true,                // Include tooltips
                false                // Exclude URLs
        );

        // Customize the chart
        PiePlot plot = (PiePlot) pieChart.getPlot();

        // Set custom labels in legend with data values
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})",     // Label format: {category}: {value} ({percentage})
                new DecimalFormat("0"),  // Format for value
                new DecimalFormat("0%")  // Format for percentage
        ));

        // Set custom colors for each section using the converted Color objects
        for (int i = 0; i < categories.length; i++) {
            plot.setSectionPaint(categories[i], colors[i]);
        }

        // Save the pie chart as an image
        try {
            File pieChartFile = new File("PieChartWithHTMLColors.png");
            ChartUtils.saveChartAsPNG(pieChartFile, pieChart, 400, 300);
            System.out.println("Pie chart saved as PieChartWithHTMLColors.png");
        } catch (IOException e) {
            System.err.println("Error saving pie chart image: " + e.getMessage());
        }
    }
}
