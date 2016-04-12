package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;

import it.polito.tdp.spellchecker.model.*;
import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SpellCheckerController
{
	private Dictionary d;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLanguage;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button buttonSpellCheck;

    @FXML
    private TextFlow txtResult;

    @FXML
    private Label labelErrors;

    @FXML
    private Button buttonClear;

    @FXML
    private Label labelTime;

    @FXML
    void doClearText(ActionEvent event) 
    {
    	//txtResult.reset
    	txtTesto.setText("");
    	labelErrors.setVisible(false);
    	labelTime.setVisible(false);
    	txtResult.getChildren().clear();
    	boxLanguage.setDisable(false);
    }

    @FXML
    void doSpellCheck(ActionEvent event) 
    {
    	int errori = 0;
    	
    	if(boxLanguage.getValue().compareTo("English") == 0)
    	{
    		d = new EnglishDictionary();
    	}
    	if(boxLanguage.getValue().compareTo("Italian") == 0)
    	{
    		d = new ItalianDictionary();
    	}
    	d.loadDictionary();
    	long time = System.nanoTime();
    	List<RichWord> rw = d.spellCheckText(getListSpell(txtTesto.getText()));
    	long time1 = System.nanoTime();
    	String t = String.valueOf((double)(time1 - time)/1000000000);
    	labelTime.setText("Spell check completed in "+ t +" seconds");
    	String s = "";
    	for (RichWord richWord : rw) 
		{
    		Text tt = new Text(richWord.toString()+" ");
			if(!richWord.isCorretta())
			{
				tt.setFill(Color.RED);
				errori++;
			}
			else
			{
				tt.setFill(Color.BLACK);
			}
			 txtResult.getChildren().add(tt); 
		}
    	labelErrors.setText(String.format("Ci sono %d errori", errori));
    	labelErrors.setVisible(true);
    	labelTime.setVisible(true);
    	boxLanguage.setDisable(true);
    	
    }

    @FXML
    void initialize() 
    {
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert buttonSpellCheck != null : "fx:id=\"buttonSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labelErrors != null : "fx:id=\"labelErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert buttonClear != null : "fx:id=\"buttonClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labelTime != null : "fx:id=\"labelTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        
        boxLanguage.getItems().addAll("English", "Italian");
        labelErrors.setVisible(false);
        labelTime.setVisible(false);
    }
	private List<String> getListSpell(String frase)
	{
		List<String> parole = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(frase, " ");
		while(st.hasMoreTokens())
		{
			String word = st.nextToken().toLowerCase();
			if(!Character.isLetter(word.charAt(word.length()-1)) )
			{
				word = word.substring(0, word.length()-1);
			
			}
			parole.add(word);
		}
		return parole;
	}

}
