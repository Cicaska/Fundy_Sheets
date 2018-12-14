package com.fundy.client;

import java.io.File;

import com.fundy.ExcelParser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Main GUI frame to be displayed to the user.
 * 
 * @author Mitchell Roberts
 * Date Created: Sep 13, 2018
 */
public class FundySheetsApplication extends Application {

	private static final double MIN_WIDTH = 100;
	
	private Label inputFileLabel;
	private Label outputFileLabel;
	private FileChooser inputExcelFileChooser;
	private DirectoryChooser outputFolderChooser;
	private TextField inputTextField;
	private TextField outputTextField;
	private Button inputBrowseButton;
	private Button outputBrowseButton;
	private Button generateButton;
	
	private File inputFile;
	private File outputDir;

	public FundySheetsApplication() {
		this.inputFileLabel = new Label("Please select the input Excel file: ");
		this.outputFileLabel = new Label("Please select the output directory: ");
		
		this.inputBrowseButton = new Button("Browse...");
		this.outputBrowseButton = new Button("Browse...");
		
		this.generateButton = new Button("Generate");
		
		this.inputTextField = new TextField();
		inputTextField.setMinWidth(MIN_WIDTH);
		inputTextField.setDisable(true);
		
		this.outputTextField = new TextField();
		outputTextField.setMinWidth(MIN_WIDTH);
		outputTextField.setDisable(true);
		
		this.inputExcelFileChooser = new FileChooser();
		inputExcelFileChooser.setSelectedExtensionFilter(new ExtensionFilter("Excel files", "xls", "xlsx"));

		this.outputFolderChooser = new DirectoryChooser();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		configureButtons(primaryStage);
		primaryStage.setTitle("Fundy Sheets");
		primaryStage.setScene(new Scene(getRoot(), 450, 130));
		primaryStage.show();
	}
	
	private Pane getRoot() {
		GridPane inputPane = new GridPane();
		GridPane.setConstraints(inputFileLabel, 0, 0);
		GridPane.setConstraints(inputTextField, 0, 1);
		GridPane.setConstraints(inputBrowseButton, 0, 2);
		inputPane.getChildren().addAll(inputFileLabel, inputTextField, inputBrowseButton);
		
		GridPane outputPane = new GridPane();
		GridPane.setConstraints(outputFileLabel, 0, 0);
		GridPane.setConstraints(outputTextField, 0, 1);
		GridPane.setConstraints(outputBrowseButton, 0, 2);
		outputPane.getChildren().addAll(outputFileLabel, outputTextField, outputBrowseButton);
		
		GridPane rootPane = new GridPane();
		rootPane.setHgap(10);
		rootPane.setVgap(10);
		GridPane.setConstraints(inputPane, 0, 0);
		GridPane.setConstraints(outputPane, 2, 0);
		GridPane.setConstraints(generateButton, 1, 1);
		rootPane.getChildren().addAll(inputPane, outputPane, generateButton);
		
		return rootPane;
	}
	
	private void configureButtons(Stage stage) {
		inputBrowseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				inputFile = inputExcelFileChooser.showOpenDialog(stage);
				if(inputFile != null) {
					inputTextField.setText(inputFile.getPath());
				} else {
					inputTextField.setText("");
				}
			}
		});
		
		outputBrowseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				outputDir = outputFolderChooser.showDialog(stage);
				if(outputDir != null) {
					outputTextField.setText(outputDir.getPath());
				} else {
					outputTextField.setText("");
				}
			}
		});
		
		generateButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(validateInputAndOutput()) {
					try {
						ExcelParser.parse(inputFile, outputDir);
					} catch (Exception e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setContentText("Failed to parse Excel file: " + e.getMessage());
						alert.show();
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Input/output invalid. "
							+ "Please select a valid Excel file and output directory.");
					alert.show();
				}
			}
		});
	}
	
	private boolean validateInputAndOutput() {
		if(inputFile != null && inputFile.exists() && inputFile.isFile()
				&& (inputFile.getName().endsWith("xls") || inputFile.getName().endsWith("xlsx"))) {
			if(outputDir != null && outputDir.exists() && outputDir.isDirectory()) {
				return true;
			}
		}
		return false;
	}
}
