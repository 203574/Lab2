package it.polito.tdp.spellchecker.model;

import java.util.*;

public class TestModel 
{

	public static void main(String[] args) 
	{
		Dictionary d = new EnglishDictionary();
		d.loadDictionary();
		String s = new String("hello! i'm a big deal drone");
		/*List<String> st = d.getListSpell(s);
		List<RichWord> rw = d.spellCheckText(st);
		for (RichWord richWord : rw) 
		{
			if(!richWord.isCorretta())
			{
				System.out.println(richWord.getParola()+" ");
			}
		}*/

	}

}
