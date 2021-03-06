#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define TAM 1000

typedef struct
{
    char nomeArquivo[100], nome[100], corDoCabelo[100], corDaPele[100], corDosOlhos[100], anoNascimento[100], genero[100], homeworld[100];
    int altura;
    double peso;
}Personagens;

bool isFim(char* s);
char * substring(char* padrao, char * entrada);
long int indexOf(char *padrao, char * entrada);
char * lerCorDoCabelo(char* line);
char * lerCorDaPele(char* line);
char * lerCorDosOlhos(char* line);
char * lerAnoNascimento(char* line);
char * lerGenero(char* line);
char * lerHomeworld(char* line);
void lerPersonagem();
void printPersonagem(Personagens x);
void inserirInicio(Personagens x);
void inserirFim(Personagens x);
void inserirPos(Personagens x, int pos);
Personagens removerInicio();
Personagens removerFim();
Personagens removerPos(int pos);
Personagens personagem;
Personagens array[TAM];
int compareTo(char* a, char* b, char* c, char* d);
void swap(int a, int b);
void insertionSort();

int n = 0;

int main()
{
    char palavra[TAM][TAM];

    int i = 0;

    do                                              //preencher a matriz
    {
        fgets(palavra[i], TAM, stdin);
    }while(isFim(palavra[i++]) == false);         //testar se eh FIM
    i--;

    for(int contador = 0; contador < i; contador++)     //ler os primeiros personagens e inserir na lista
    {
        strcpy(personagem.nomeArquivo,strndup(palavra[contador],strlen(palavra[contador]) - 1));
        lerPersonagem();
        inserirFim(personagem);
    }

    insertionSort();

    for(int c = 0; c < 10; c++)
    {
        strcpy(personagem.nomeArquivo,array[c].nomeArquivo);      //printar os personagem
        printPersonagem(personagem);
    }
}

int compareTo(char* a, char* b, char* c, char* d)
{   
    int resp = 0;

    int tam = strlen(a);
    if(strlen(b) < tam)tam = strlen(b);

    for(int i = 0; i < tam; i++)
    {
        if(a[i] > b[i])
        {
            resp = 1;
            i = tam;
        }

        else if(a[i] < b[i])
        {
            resp = -1;
            i = tam;
        }
    }

    if(resp == 0 && strlen(a) != strlen(b))
    {
        if(strlen(b) > strlen(a)) resp = -1;
        else resp = 1;
    }

    else if(resp == 0)
    {
        int tam = strlen(c);
        if(strlen(d) < tam)tam = strlen(d);

        for(int i = 0; i < tam; i++)
        {
            if(c[i] > d[i])
            {
                resp = 1;
                i = tam;
            }

            else if(c[i] < d[i])
            {
                resp = -1;
                i = tam;
            }
        }

        if(resp == 0 && strlen(c) != strlen(d))
        {
            if(strlen(d) > strlen(c)) resp = -1;
            else resp = 1;
        }
    }

    return resp;
}

void swap(int a, int b)
{
    Personagens aux = array[a];
    array[a] = array[b];
    array[b] = aux;
}

void insertionSort() 
{
    for(int i = 1; i < n; i++) 
    {
        Personagens tmp = array[i];
        int j = i - 1;

        while((j >= 0) && (compareTo(array[j].anoNascimento, tmp.anoNascimento, array[j].nome, tmp.nome) > 0)) 
        {
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = tmp;

    }
}

void lerPersonagem()
{
    FILE *arq;
    arq = fopen(personagem.nomeArquivo,"rb");

    char* line = NULL;

    size_t bufsize = 1000;
    getline(&line,&bufsize,arq);

    line = strndup(line,indexOf("films",line) + 10);

    char* aux = (char *) malloc(1000 *sizeof(char));
    char* aux2 = (char *) malloc(TAM *sizeof(char));
    char* aux3 = (char *) malloc(TAM *sizeof(char));

    strcpy(personagem.nome,strndup(&line[10],indexOf("'height':",line) - 13));

    strcpy(aux,strndup(&line[indexOf("'height':",line) + 11],indexOf("'mass':",&line[indexOf("'height':",line) + 11]) - 3));
    if(aux[0] == 'u')personagem.altura = 0;
    else sscanf(aux, "%d", &personagem.altura);

    strcpy(aux,strndup(&line[indexOf("'mass':",line) + 9],indexOf("'hair_color':",&line[indexOf("'mass':",line) + 9]) - 3));

    int tam = strlen(aux);

    for(int i = 0; i < tam; i++)
    {
        if(aux[i] == ',')
        {
            aux2 = strndup(aux, i);
            strcpy(aux3,&aux[i + 1]);
            strcat(aux2,aux3);
            strcpy(aux,aux2);
        }
    }

    if(aux[0] == 'u')personagem.peso = 0;
    else sscanf(aux, "%lf", &personagem.peso);
    aux = NULL;
    free(aux);
    aux2 = NULL;
    free(aux2);
    aux3 = NULL;
    free(aux3);
    
    strcpy(personagem.corDoCabelo,lerCorDoCabelo(line));

    strcpy(personagem.corDaPele,lerCorDaPele(line));

    strcpy(personagem.corDosOlhos,lerCorDosOlhos(line));

    strcpy(personagem.anoNascimento,lerAnoNascimento(line));

    strcpy(personagem.genero,lerGenero(line));

    strcpy(personagem.homeworld,lerHomeworld(line));

    /*printf(" ## %s ## ",personagem.nome);
    printf("%d ## ",personagem.altura);
    printf("%g ## ",personagem.peso);
    printf("%s ## ",personagem.corDoCabelo);
    printf("%s ## ",personagem.corDaPele);
    printf("%s ## ",personagem.corDosOlhos);
    printf("%s ## ",personagem.anoNascimento);
    printf("%s ## ",personagem.genero);
    printf("%s ## \n",personagem.homeworld);*/
}

void printPersonagem(Personagens x)
{
    personagem = x;
    lerPersonagem();
    
    printf(" ## %s ## ",personagem.nome);
    printf("%d ## ",personagem.altura);
    printf("%g ## ",personagem.peso);
    printf("%s ## ",personagem.corDoCabelo);
    printf("%s ## ",personagem.corDaPele);
    printf("%s ## ",personagem.corDosOlhos);
    printf("%s ## ",personagem.anoNascimento);
    printf("%s ## ",personagem.genero);
    printf("%s ## \n",personagem.homeworld);
}

char * lerCorDoCabelo(char* line)
{
    char* aux = (char *) malloc(1000 *sizeof(char));

    strcpy(aux,strndup(&line[indexOf("'hair_color':",line) + 15],indexOf("'skin_color':",&line[indexOf("'hair_color':",line) + 15]) - 3));

    return aux;
}

char * lerCorDaPele(char* line)
{
    char* aux = (char *) malloc(1000 *sizeof(char));

    strcpy(aux,strndup(&line[indexOf("'skin_color':",line) + 15],indexOf("'eye_color':",&line[indexOf("'skin_color':",line) + 15]) - 3));

    return aux;
}

char * lerCorDosOlhos(char* line)
{
    char* aux = (char *) malloc(1000 *sizeof(char));

    strcpy(aux,strndup(&line[indexOf("'eye_color':",line) + 14],indexOf("'birth_year':",&line[indexOf("'eye_color':",line) + 14]) - 3));

    return aux;
}

char * lerAnoNascimento(char* line)
{
    char* aux = (char *) malloc(1000 *sizeof(char));

    strcpy(aux,strndup(&line[indexOf("'birth_year':",line) + 15],indexOf("'gender':",&line[indexOf("'birth_year':",line) + 15]) - 3));

    return aux;
}

char * lerGenero(char* line)
{
    char* aux = (char *) malloc(1000 *sizeof(char));

    strcpy(aux,strndup(&line[indexOf("'gender':",line) + 11],indexOf("'homeworld':",&line[indexOf("'gender':",line) + 11]) - 3));

    return aux;
}

char * lerHomeworld(char* line)
{
    char* aux = (char *) malloc(1000 *sizeof(char));

    strcpy(aux,strndup(&line[indexOf("'homeworld':",line) + 14],indexOf("'films':",&line[indexOf("'homeworld':",line) + 14]) - 3));

    return aux;
}

char * substring(char* padrao, char * entrada)
{
    char *pointer = strstr(entrada,padrao);

    return strdup(pointer);
}

long int indexOf(char *padrao, char * entrada)
{
   char *pointer = strstr(entrada,padrao);

   return pointer - entrada;
}

bool isFim(char* s)
{
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');    //retornar true ou false para se eh o FIM
}

void inserirInicio(Personagens x) 
{
    
   for(int i = n; i > 0; i--)
   {
      array[i] = array[i-1];
   }

   array[0] = x;
   n++;
}

void inserirFim(Personagens x) 
{

   array[n] = x;
   n++;
}

void inserirPos(Personagens x, int pos) 
{
   int i;

   for(i = n; i > pos; i--)
   {
      array[i] = array[i-1];
   }
 
   array[pos] = x;
   n++;
}

Personagens removerInicio() 
{
    int i;
    Personagens resp;

    resp = array[0];

    n--;
    
    for(i = 0; i < n; i++)
    {
        array[i] = array[i+1];
    }

    return resp;
}

Personagens removerFim() 
{
    return array[--n];
}

Personagens removerPos(int pos) 
{
   int i;
   Personagens resp;

   resp = array[pos];
   n--;
 
   for(i = pos; i < n; i++)
   {
      array[i] = array[i+1];
   }

   return resp;
}