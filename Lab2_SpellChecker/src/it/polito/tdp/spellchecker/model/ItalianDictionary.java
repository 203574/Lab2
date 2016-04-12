package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ItalianDictionary extends Dictionary
{
	@Override
	public void loadDictionary() 
	{
		/*try 
		{
			FileReader fr = new FileReader("rsc/Italian.txt"); BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null)
			{
			// Aggiungere word alla struttura dati
				dizionario.add(word.toLowerCase());
			} 
			br.close();
		} 
		catch (IOException e)
		{
			System.out.println("Errore nella lettura del file");
		}*/
		String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root&password=root";
		try 
		{
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			String sql = "select * from parola";
			ResultSet res = st.executeQuery(sql);
			while(res.next())
			{
				String word = res.getString("nome");
				dizionario.add(word.toLowerCase());
			}
			res.close();
			conn.close();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.loadDictionary();
	}

	@Override
	public List<RichWord> spellCheckText(List<String> inputTextList) 
	{
		String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root&password=root";
		List<RichWord> rich = new ArrayList<RichWord>();
		boolean esiste = false;
		try 
		{
				Connection conn = DriverManager.getConnection(jdbcURL);
				Statement st = conn.createStatement();
				for (String string : inputTextList) 
				{
					String sql = "select nome from parola where nome = \""+string+"\"";
					ResultSet res = st.executeQuery(sql);
					if(res.next())
					{
						esiste = true;
						rich.add(new RichWord(string, esiste));
					}
					else
					{
						rich.add(new RichWord(string, esiste));
					}
					res.close();
				}
				conn.close();
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
			
		return super.spellCheckText(inputTextList);
	}
	
}
