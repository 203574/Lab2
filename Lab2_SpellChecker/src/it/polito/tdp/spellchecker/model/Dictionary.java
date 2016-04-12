package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Dictionary 
{
	
	protected HashSet<String> dizionario = new HashSet<String>();

	public void loadDictionary()
	{
		/*dizionario.sort(new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				// TODO Auto-generated method stub
				return s1.compareToIgnoreCase(s2);
			}

		});;*/

	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList)
	{
		List<RichWord> rich = new ArrayList<RichWord>();
		for (String s : inputTextList)
		{
			/*int i = 0;
			int f = dizionario.size()-1;
			int m =(int)(f/2);
			boolean esiste = search(dizionario, s, i, f, m);
			rich.add(new RichWord(s, esiste));*/
			if(dizionario.contains(s))
			{
				rich.add(new RichWord(s, true));
			}
			else
			{
				rich.add(new RichWord(s, false));
			}
		}
		return rich;
	}
	
	/*private boolean search(HashSet<String> dizionarioItaliano2, String s, int i, int f, int m) {
		

		if(f-i<=1)
			return s.compareToIgnoreCase(dizionarioItaliano2.get(f)) == 0 || s.compareToIgnoreCase(dizionarioItaliano2.get(i)) ==0;
		int cmp = s.compareToIgnoreCase(dizionarioItaliano2.get(m));
		if( cmp == 0)
		{
			return true;
		}
		else if(cmp < 0)
		{
			f=m;
			m = (int)((f-i)/2)+i;
		}
		else
		{
			i=m;
			m = (int)((f-i)/2)+i;
		}
		
		return search(dizionarioItaliano2,s, i, f, m);	
		
	}*/


}
