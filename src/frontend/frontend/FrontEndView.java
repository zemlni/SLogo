package frontend.frontend;

import frontend.IConfigurableView;
import frontend.IControllableView;
import frontend.commands.CommandsConfig;
import frontend.commands.CommandsController;
import frontend.commands.CommandsView;
import frontend.history.HistoryController;
import frontend.history.HistoryView;
import frontend.input.ScriptController;
import frontend.input.ScriptView;
import frontend.input.ShellController;
import frontend.input.ShellView;
import frontend.turtlescreen.TurtleScreenController;
import frontend.turtlescreen.TurtleScreenView;
import frontend.variables.VariablesConfig;
import frontend.variables.VariablesController;
import frontend.variables.VariablesView;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import utils.javafx.FX;

public class FrontEndView extends SplitPane implements IControllableView,
	IConfigurableView {

	private TurtleScreenView turtleScreenView;
	private ScriptView scriptView;
	private ShellView shellView;
	private VariablesView variablesView;
	private CommandsView commandsView;
	private HistoryView historyView;
	
	private TabPane inputTabPane;
	
	private FrontEndController controller;
	
	public FrontEndView() {
		this(null);
	}
	
	public FrontEndView(FrontEndConfig config) {
		if (config == null) {
			turtleScreenView = new TurtleScreenView();
			scriptView = new ScriptView();
			shellView = new ShellView();
			variablesView = new VariablesView();
			commandsView = new CommandsView();
			historyView = new HistoryView(); 
		} else {
			turtleScreenView = new TurtleScreenView(config.getTurtleScreenConfig());
			scriptView = new ScriptView();
			shellView = new ShellView();
			variablesView = new VariablesView();
			commandsView = new CommandsView();
			historyView = new HistoryView(config.getHistoryConfig()); 
		}
		
		this.setOrientation(Orientation.HORIZONTAL);
		
		// left pane: turtle screen and input windows
		SplitPane leftPane = new SplitPane();
		SplitPane.setResizableWithParent(leftPane, true);
		leftPane.setOrientation(Orientation.VERTICAL);
		
		inputTabPane = new TabPane();
		inputTabPane.setSide(Side.LEFT);
		inputTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab scriptTab = FX.tab("Script", scriptView);
		Tab shellTab  = FX.tab("Shell", shellView);
		inputTabPane.getTabs().addAll(scriptTab, shellTab);

		leftPane.getItems().addAll(turtleScreenView, inputTabPane);
		
		// right pane: variables, commands, and history windows
		VBox rightPane = new VBox();
		SplitPane.setResizableWithParent(rightPane, true);
		rightPane.setMinWidth(0.0);
		
		TitledPane variablesPane = FX.titledPane("Variables", variablesView);
		TitledPane commandsPane = FX.titledPane("Commands", commandsView);
		TitledPane historyPane = FX.titledPane("History", historyView);
		
		rightPane.getChildren().addAll(
					variablesPane, commandsPane, historyPane
				);
		
		this.getItems().addAll(leftPane, rightPane);
		
		if (config == null) { 
			controller = new FrontEndController(this); 
		} else {
			controller = new FrontEndController(this, config.getVariablesConfig(), config.getCommandsConfig());
		}
	}
	
	public TurtleScreenController getTurtleScreenController() {
		return turtleScreenView.getController();
	}
	public ShellController getShellController() {
		return shellView.getController();
	}
	public ScriptController getScriptController() {
		return scriptView.getController();
	}
	public VariablesController getVariablesController() {
		return variablesView.getController();
	}
	public CommandsController getCommandsController() {
		return commandsView.getController();
	}
	public HistoryController getHistoryController() {
		return historyView.getController();
	}
	public TabPane getInputTabPane() {
		return inputTabPane;
	}
	

	@Override
	public FrontEndController getController() {
		return controller;
	}

	@Override
	public FrontEndConfig getConfig() {
		FrontEndConfig config = new FrontEndConfig(
					turtleScreenView.getConfig(),
					new VariablesConfig(controller.getVariables()),
					new CommandsConfig(controller.getCommands()),
					historyView.getConfig()
				);
		return config;
	}
	
}
