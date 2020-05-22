#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define TAM 1000
//Declaracao de elementos privados
typedef struct
{
    char nomeArquivo[100];
    char nome[50];
    char corDoCabelo[50];
    char corDaPele[50];
    char corDosOlhos[50];
    char anoNascimento[50];
    char genero[50];
    char homeworld[50];
    int altura;
    double peso;
} Personagens;

//Declaracao de funcoes
bool isFim(char *s);
char *substring(char *padrao, char *entrada);
long int indexOf(char *padrao, char *entrada);
char *lerCorDoCabelo(char *line);
char *lerCorDaPele(char *line);
char *lerCorDosOlhos(char *line);
char *lerAnoNascimento(char *line);
char *lerGenero(char *line);
char *lerHomeworld(char *line);
void lerPersonagem();
void escreverPersonagem(Personagens x);
void inserirFim(Personagens x);
int binarySearch(int l, int r, char *nome);

Personagens personagem;
Personagens array[TAM];
int compareTo(double avatar_1, double avatar_2, char *avatar_3, char *avatar_4);
void swap(int a, int b);
void shellsort();
void inserirCor(int cor, int h);
int n = 0;

int main()
{
    char palavra[TAM][TAM];

    int i = 0;

    //Preencher array
    do
    {
        fgets(palavra[i], TAM, stdin);
    } while (isFim(palavra[i++]) == false);
    //Testar palavra FIM
    i--;

    //Inserir personagens na lista
    for (int contador = 0; contador < i; contador++)
    {
        strcpy(personagem.nomeArquivo, strndup(palavra[contador], strlen(palavra[contador]) - 1));
        //Ler personagens a serem inseridos na lista
        lerPersonagem();
        inserirFim(personagem);
    }
    //Chamando funcao de ordenacao
    shellsort();

    for (int c = 0; c < n; c++)
    {
        strcpy(personagem.nomeArquivo, array[c].nomeArquivo);
        escreverPersonagem(personagem);
    }
}

//Fucao comparar personagens

int compareTo(double avatar_1, double avatar_2, char *avatar_3, char *avatar_4)
{
    int resposta = 0;

    if (avatar_1 > avatar_2)
    {
        resposta = 1;
    }
    else if (avatar_1 < avatar_2)
    {
        resposta = -1;
    }

    if (resposta == 0)
    {
        int tamanho = strlen(avatar_3);
        if (strlen(avatar_4) < tamanho)
        {
            tamanho = strlen(avatar_4);
        }

        for (int i = 0; i < tamanho; i++)
        {

            if (avatar_3[i] > avatar_4[i])
            {
                resposta = 1;
                i = tamanho;
            }
            else if (avatar_3[i] < avatar_4[i])
            {
                resposta = -1;
                i = tamanho;
            }
        }

        if (resposta == 0 && strlen(avatar_3) != strlen(avatar_4))
        {

            if (strlen(avatar_4) > strlen(avatar_3))
            {
                resposta = -1;
            }
            else
            {
                resposta = 1;
            }
        }
    }
    return resposta;
}

//Troca de personagens
void swap(int avatar_1, int avatar_2)
{

    Personagens aux = array[avatar_1];
    array[avatar_1] = array[avatar_2];
    array[avatar_2] = aux;
}

void inserirCor(int cor, int h)
{

    for (int i = (h + cor); i < n; i += h)
    {

        Personagens tmp = array[i];
        int j = i - h;

        while ((j >= 0) && compareTo(array[j].peso, tmp.peso, array[j].nome, tmp.nome) > 0)
        {

            array[j + h] = array[j];
            j -= h;
        }
        array[j + h] = tmp;
    }
}
//Ordenacao ShellSort
void shellsort()
{

    int h = 1;

    do
    {
        h = (h * 3) + 1;
    } while (h < n);

    do
    {
        h /= 3;
        for (int cor = 0; cor < h; cor++)
        {
            inserirCor(cor, h);
        }
    } while (h != 1);
}

void lerPersonagem()
{
    FILE *arq;
    arq = fopen(personagem.nomeArquivo, "rb");

    char *line = NULL;

    size_t bufsize = 1000;
    getline(&line, &bufsize, arq);

    line = strndup(line, indexOf("films", line) + 10);

    char *aux = (char *)malloc(1000 * sizeof(char));
    char *aux2 = (char *)malloc(TAM * sizeof(char));
    char *aux3 = (char *)malloc(TAM * sizeof(char));

    strcpy(personagem.nome, strndup(&line[10], indexOf("'height':", line) - 13));

    strcpy(aux, strndup(&line[indexOf("'height':", line) + 11], indexOf("'mass':", &line[indexOf("'height':", line) + 11]) - 3));
    if (aux[0] == 'u')
        personagem.altura = 0;
    else
        sscanf(aux, "%d", &personagem.altura);

    strcpy(aux, strndup(&line[indexOf("'mass':", line) + 9], indexOf("'hair_color':", &line[indexOf("'mass':", line) + 9]) - 3));

    int tam = strlen(aux);

    for (int i = 0; i < tam; i++)
    {
        if (aux[i] == ',')
        {
            aux2 = strndup(aux, i);
            strcpy(aux3, &aux[i + 1]);
            strcat(aux2, aux3);
            strcpy(aux, aux2);
        }
    }

    if (aux[0] == 'u')
        personagem.peso = 0;
    else
        sscanf(aux, "%lf", &personagem.peso);
    aux = NULL;
    free(aux);
    aux2 = NULL;
    free(aux2);
    aux3 = NULL;
    free(aux3);

    strcpy(personagem.corDoCabelo, lerCorDoCabelo(line));

    strcpy(personagem.corDaPele, lerCorDaPele(line));

    strcpy(personagem.corDosOlhos, lerCorDosOlhos(line));

    strcpy(personagem.anoNascimento, lerAnoNascimento(line));

    strcpy(personagem.genero, lerGenero(line));

    strcpy(personagem.homeworld, lerHomeworld(line));
}

char *lerCorDoCabelo(char *line)
{ //Separar cor do cabelo
    char *aux = (char *)malloc(1000 * sizeof(char));

    strcpy(aux, strndup(&line[indexOf("'hair_color':", line) + 15], indexOf("'skin_color':", &line[indexOf("'hair_color':", line) + 15]) - 3));

    return aux;
}

char *lerCorDaPele(char *line)
{ //Separar cor da pele
    char *aux = (char *)malloc(1000 * sizeof(char));

    strcpy(aux, strndup(&line[indexOf("'skin_color':", line) + 15], indexOf("'eye_color':", &line[indexOf("'skin_color':", line) + 15]) - 3));

    return aux;
}

char *lerCorDosOlhos(char *line)
{ //Separar cor dos olhos
    char *aux = (char *)malloc(1000 * sizeof(char));

    strcpy(aux, strndup(&line[indexOf("'eye_color':", line) + 14], indexOf("'birth_year':", &line[indexOf("'eye_color':", line) + 14]) - 3));

    return aux;
}

char *lerAnoNascimento(char *line)
{ //Separar ano nascimento
    char *aux = (char *)malloc(1000 * sizeof(char));

    strcpy(aux, strndup(&line[indexOf("'birth_year':", line) + 15], indexOf("'gender':", &line[indexOf("'birth_year':", line) + 15]) - 3));

    return aux;
}

char *lerGenero(char *line)
{ //Separar genero
    char *aux = (char *)malloc(1000 * sizeof(char));

    strcpy(aux, strndup(&line[indexOf("'gender':", line) + 11], indexOf("'homeworld':", &line[indexOf("'gender':", line) + 11]) - 3));

    return aux;
}

char *lerHomeworld(char *line)
{ //Separar planeta natal
    char *aux = (char *)malloc(1000 * sizeof(char));

    strcpy(aux, strndup(&line[indexOf("'homeworld':", line) + 14], indexOf("'films':", &line[indexOf("'homeworld':", line) + 14]) - 3));

    return aux;
}

char *substring(char *padrao, char *entrada)
{ //Criacao de substring
    char *pointer = strstr(entrada, padrao);

    return strdup(pointer);
}

long int indexOf(char *padrao, char *entrada)
{ //uso indexOf
    char *pointer = strstr(entrada, padrao);

    return pointer - entrada;
}

bool isFim(char *s)
{ //Identificar FIM
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

void inserirFim(Personagens x)
{
    //Inserir personagens iniciais na lista
    array[n] = x;
    n++;
}

void escreverPersonagem(Personagens x)
{
    personagem = x;
    lerPersonagem();

    printf(" ## %s ## ", personagem.nome);
    printf("%d ## ", personagem.altura);
    printf("%g ## ", personagem.peso);
    printf("%s ## ", personagem.corDoCabelo);
    printf("%s ## ", personagem.corDaPele);
    printf("%s ## ", personagem.corDosOlhos);
    printf("%s ## ", personagem.anoNascimento);
    printf("%s ## ", personagem.genero);
    printf("%s ## \n", personagem.homeworld);
}