package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.io.IOException;


public class Main extends Application{
	Button button1;
	Button button2;
	
	public static void main(String[] args) throws IOException {
		launch(args);
	} //End Main

	//Methods	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Word Frequency Counter");
			button1 = new Button();
			button1.setText("Calculate Word Frequency");
			button1.setOnAction(e -> wordCount());
			
			button2 = new Button();
			button2.setText("Exit Program");
			button2.setOnAction(e -> Platform.exit());
			
			HBox layout = new HBox();
			layout.setAlignment(Pos.CENTER);
			layout.setPadding(new Insets(15, 12, 15, 12));
			layout.setSpacing(10);
			layout.setStyle("=fx-background-colo: #336699;");
			
			layout.getChildren().add(button1);
			layout.getChildren().add(button2);
			Scene scene = new Scene(layout,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void wordCount() {
		new WordFreq().wordCount();
	}
} //End Class
