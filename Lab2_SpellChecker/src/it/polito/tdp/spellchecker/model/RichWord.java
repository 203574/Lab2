package it.polito.tdp.spellchecker.model;

public class RichWord 
{
	private String parola;
	private boolean corretta;
	
	public RichWord(String parola, boolean c)
	{
		this.parola = parola;
		corretta = c;
	}

	public String getParola() 
	{
		return parola;
	}

	public boolean isCorretta()
	{
		return corretta;
	}

	@Override
	public String toString() 
	{
		return parola;
	}
	
	
}
