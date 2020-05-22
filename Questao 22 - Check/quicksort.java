import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;

class Personagens
{
    private String nomeArquivo, nome, corDoCabelo, corDaPele, corDosOlhos, anoNascimento, genero, homeworld;
    private int altura;
    private double peso;

	public String getNomeArquivo() 
  {
		return this.nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) 
  {
		this.nomeArquivo = nomeArquivo;
	}

	public int getAltura() 
  {
		return this.altura;
	}

	public void setAltura(int altura) 
  {
		this.altura = altura;
	}

	public double getPeso() 
  {
		return this.peso;
	}

	public void setPeso(double peso) 
  {
		this.peso = peso;
	}

  public String getNome() 
  {
		return this.nome;
	}

	public void setNome(String nome) 
  {
		this.nome = nome;
	}

  public String getCorDoCabelo() 
  {
		return this.corDoCabelo;
	}

	public void setCorDoCabelo(String corDoCabelo) 
  {
		this.corDoCabelo = corDoCabelo;
	}

  public String getCorDaPele() 
  {
		return this.corDaPele;
	}

	public void setCorDaPele(String corDaPele) 
  {
		this.corDaPele = corDaPele;
	}

  public String getCorDosOlhos() 
  {
		return this.corDosOlhos;
	}

	public void setCorDosOlhos(String corDosOlhos) 
  {
		this.corDosOlhos = corDosOlhos;
	}

  public String getAnoNascimento() 
  {
		return this.anoNascimento;
	}

	public void setAnoNascimento(String anoNascimento) 
  {
		this.anoNascimento = anoNascimento;
	}

  public String getGenero() 
  {
		return this.genero;
	}

	public void setGenero(String genero) 
  {
		this.genero = genero;
	}

  public String getHomeworld() 
  {
		return this.homeworld;
	}

	public void setHomeworld(String homeworld) 
  {
		this.homeworld = homeworld;
	}

    public void lerPersonagem()throws Exception
    {
        FileReader file = new FileReader(this.nomeArquivo);

        BufferedReader br = new BufferedReader(file);
        String line = "";
        String aux = "";

        line = br.readLine();
        
        line = line.substring(0,line.indexOf("films"));
        line = line.replaceAll("'","");

        setNome(line.substring(line.indexOf("name: ") + 6,line.indexOf(",")));
        line = line.substring(line.indexOf(",") + 1);

        aux = line.substring(line.indexOf("height: ") + 8,line.indexOf(","));

        if(aux.contains("unknown"))setAltura(0);
        else setAltura(Integer.parseInt(aux));

        line = line.substring(line.indexOf(",") + 1);

        aux = line.substring(line.indexOf("mass: ") + 6,line.indexOf("hair_color:") - 2);

        if(aux.contains(","))aux = aux.replace(",","");
        if(aux.contains("unknown"))setPeso(0);
        else setPeso(Double.parseDouble(aux));

        line = line.substring(line.indexOf("hair_color:"));

        setCorDoCabelo(line.substring(line.indexOf("hair_color: ") + 12,line.indexOf("skin_color:") - 2));
        line = line.substring(line.indexOf("skin_color:"));

        setCorDaPele(line.substring(line.indexOf("skin_color: ") + 12,line.indexOf("eye_color:") - 2));
        line = line.substring(line.indexOf("eye_color:"));

        setCorDosOlhos(line.substring(line.indexOf("eye_color: ") + 11,line.indexOf("birth_year:") - 2));
        line = line.substring(line.indexOf("birth_year:"));

        setAnoNascimento(line.substring(line.indexOf("birth_year: ") + 12,line.indexOf(",")));
        line = line.substring(line.indexOf(",") + 1);

        setGenero(line.substring(line.indexOf("gender: ") + 8,line.indexOf(",")));
        line = line.substring(line.indexOf(",") + 1);

        setHomeworld(line.substring(line.indexOf("homeworld: ") + 11,line.indexOf(",")));
        line = line.substring(line.indexOf(",") + 1);        
    }
}

class Lista  //lista de personagems
{
    private static Personagens array[];
    private static int n;
 
    public Lista () 
    {
        array = new Personagens[1000];
        n = 0;
    }

    public Lista (int tamanho)
    {
       array = new Personagens[tamanho];
       n = 0;
    }

    public static int getN()
    {
        return n;
    }

    public static void inserirInicio(Personagens x)
    {
        for(int i = n; i > 0; i--)
        {
            array[i] = array[i-1];
        }       
        array[0] = x;
        n++;
    }

    public static void inserirFinal(Personagens x)
    {
       array[n++] = x;
    }

    public static void inserir(Personagens x, int pos)
    {
       for(int i = n; i > pos; i--)
       {
          array[i] = array[i-1];
       }
       array[pos] = x;
       n++;
    }

    public static Personagens removerInicio()
    {
       
        Personagens aux = array[0];
       n--;

       for(int i = 0; i < n; i++)
       {
          array[i] = array[i+1];
       }
       return aux;
    }

    public static Personagens removerFinal()
    {
       return array[--n];
    }

    public static Personagens remover(int pos)
    {
       Personagens aux = array[pos];
       n--;

       for(int i = pos; i < n; i++)
       {
            array[i] = array[i+1];
       }
        return aux;
    }

    public static int compCabelo(Personagens a, Personagens b)
    {
        int resp = 0;

        String cabelo1 = a.getCorDoCabelo();
        String cabelo2 = b.getCorDoCabelo();

        int tam = cabelo1.length();
        if(cabelo2.length() < tam)tam = cabelo2.length();

        for(int i = 0; i < tam; i++)
        {
            if(cabelo1.charAt(i) > cabelo2.charAt(i))
            {
                resp = 1;
                i = tam;
            }

            else if(cabelo1.charAt(i) < cabelo2.charAt(i))
            {
                resp = -1;
                i = tam;
            }
        }

        if(resp == 0 && cabelo1.length() != cabelo2.length())
        {
            if(cabelo1.length() > cabelo2.length()) resp = -1;
            else resp = 1;
        }

        if(resp == 0)
        {
            String nome1 = a.getNome();
            String nome2 = b.getNome();

            tam = nome1.length();
            if(nome2.length() < tam)tam = nome2.length();

            for(int i = 0; i < tam; i++)
            {
                if(nome1.charAt(i) > nome2.charAt(i))
                {
                    resp = 1;
                    i = tam;
                }

                else if(nome1.charAt(i) < nome2.charAt(i))
                {
                    resp = -1;
                    i = tam;
                }
            }

            if(resp == 0 && nome1.length() != nome2.length())
            {
                if(nome1.length() > nome2.length()) resp = -1;
                else resp = 1;
            }
        }

        return resp;
    }
    public static void swap(int a, int b)
    {
        Personagens tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;

    }

    public static void quicksort()throws Exception
    {
        for(int i = 0; i < n; i++)
        {
            array[i].lerPersonagem();
        }

        quicksort(0, n-1);
    }
      
    public static void quicksort(int esq, int dir) 
    {
        int i = esq, j = dir;
        Personagens pivo = array[(dir+esq)/2];
        
        while(i <= j) 
        {
            while(compCabelo(pivo,array[i]) > 0)i++;
            while(compCabelo(array[j],pivo) > 0)j--;
            if(i <= j) 
            {
                swap(i,j);
                i++;
                j--;
            }
        }
        
        if (esq < j)quicksort(esq, j);
        if (i < dir)quicksort(i, dir);
    }

    public static void escrever()throws Exception
    {
        for(int i = 0; i < 10; i++)
        {
            array[i].lerPersonagem();
            System.out.printf(" ## %s ## %d ## ",array[i].getNome(),array[i].getAltura());

            if(array[i].getPeso() == (int)array[i].getPeso())System.out.printf("%d ## ",(int)array[i].getPeso());
            else MyIO.printf("%s ## ",array[i].getPeso());
            
            System.out.printf("%s ## %s ## %s ## %s ## %s ## %s ## \n",array[i].getCorDoCabelo(),array[i].getCorDaPele()
            ,array[i].getCorDosOlhos(),array[i].getAnoNascimento(),array[i].getGenero(),array[i].getHomeworld());
        }
    }
}

public class quicksort
{
    public static boolean isFim(String s)
    {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M'); //descobrir se � o FIM
    } 
    public static void main(String []agrs)throws Exception
    {
        Lista lista = new Lista();              //inicializa a lista
        Personagens[] personagem = new Personagens[100];          //array de personagens
        MyIO.setCharset("UTF-8");

        String[] palavra = new String[1000];

        int contador = 0;
        
        do
        {
            palavra[contador] = MyIO.readLine();           //ler as linhas
        }while(isFim(palavra[contador++]) == false);
        contador--;

        for(int i = 0; i < contador;i++)
        {
            personagem[i] = new Personagens();
            personagem[i].setNomeArquivo(palavra[i]);
            lista.inserirFinal(personagem[i]);
        }
        
        lista.quicksort();
        lista.escrever();       //printa os personagens da lista
    }
}
