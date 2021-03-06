
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.File;
//import java.util.Scanner;

class Characters {

    // Declaracao de elementos privados
    private String fileName;
    private String name;
    private String height;
    private String weight;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String genre;
    private String homeWorld;

    // Sets and Gets
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHairColor() {
        return this.hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return this.skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return this.eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return this.birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getHomeWorld() {
        return this.homeWorld;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    Characters() {
        this.fileName = "";
        this.name = "";
        this.height = "";
        this.weight = "";
        this.skinColor = "";
        this.hairColor = "";
        this.eyeColor = "";
        this.birthYear = "";
        this.genre = "";
        this.homeWorld = "";
    }

    // Ler arquivo txt
    public void readCharacter(String fileName) {
        try {
            FileReader arq = new FileReader(fileName);
            BufferedReader readFile = new BufferedReader(arq);
            String line = readFile.readLine();

            // MyIO.println(line);
            // Chamada de funcoes para separar dados
            nameSetUp(line);
            heightSetUp(line);
            massSetUp(line);
            hair_colorSetUp(line);
            skin_colorSetUp(line);
            eye_colorSetUp(line);
            birth_yearSetUp(line);
            genderSetUp(line);
            homeworldSetUp(line);
            readFile.close();
            arq.close();
        } catch (IOException e) {
        }
    }

    // Separar nome
    public void nameSetUp(String line) {

        int nameBegin = line.indexOf("'name'");
        int nameEnd = line.indexOf("'height'", nameBegin);
        String name = line.substring(nameBegin + 7, nameEnd).trim().replace(",", "").replace("'", "");

        this.name = name;
        // MyIO.println("##" + name);
    }

    // Separar altura
    public void heightSetUp(String line) {

        int heightBegin = line.indexOf("'height'");
        int heightEnd = line.indexOf("'mass'", heightBegin);
        String height = line.substring(heightBegin + 10, heightEnd).trim().replace(",", "").replace("'", "")
                .replace("unknown", "0");

        this.height = height;
        // MyIO.println("##" + height);
    }

    // Separar peso
    public void massSetUp(String line) {

        int massBegin = line.indexOf("'mass'");
        int massEnd = line.indexOf("'hair_color'", massBegin);
        String mass = line.substring(massBegin + 8, massEnd).trim().replace(",", "").replace("'", "").replace("unknown",
                "0");

        this.weight = mass;
        // MyIO.println("##" + mass);
    }

    // Separar cor do cabelo
    public void hair_colorSetUp(String line) {

        int hair_colorBegin = line.indexOf("'hair_color'");
        int hair_colorEnd = line.indexOf("'skin_color'", hair_colorBegin);
        String hair_color = line.substring(hair_colorBegin + 14, hair_colorEnd).trim().replace(",", "").replace("'",
                "");

        this.hairColor = hair_color;
        // MyIO.println("##" + hair_color);
    }

    // Separar cor da pele
    public void skin_colorSetUp(String line) {

        int skin_colorBegin = line.indexOf("'skin_color'");
        int skin_colorEnd = line.indexOf("'eye_color'", skin_colorBegin);
        String skin_color = line.substring(skin_colorBegin + 15, skin_colorEnd).trim().replace(",", "").replace("'",
                "");

        this.skinColor = skin_color;
        // MyIO.println("##" + skin_color);
    }

    // Separar cor do olho
    public void eye_colorSetUp(String line) {

        int eye_colorBegin = line.indexOf("'eye_color'");
        int eye_colorEnd = line.indexOf("'birth_year'", eye_colorBegin);
        String eye_color = line.substring(eye_colorBegin + 14, eye_colorEnd).trim().replace(",", "").replace("'", "");

        this.eyeColor = eye_color;
        // MyIO.println("##" + eye_color);
    }

    // Separar ano de nascimento
    public void birth_yearSetUp(String line) {

        int birth_yearBegin = line.indexOf("'birth_year'");
        int birth_yearEnd = line.indexOf("'gender'", birth_yearBegin);
        String birth_year = line.substring(birth_yearBegin + 14, birth_yearEnd).trim().replace(",", "").replace("'",
                "");

        this.birthYear = birth_year;
        // MyIO.println("##" + birth_year);
    }

    // Separar genero
    public void genderSetUp(String line) {

        int genderBegin = line.indexOf("'gender'");
        int genderEnd = line.indexOf("'homeworld'", genderBegin);
        String gender = line.substring(genderBegin + 11, genderEnd).trim().replace(",", "").replace("'", "");

        this.genre = gender;
        // MyIO.println("##" + gender);
    }

    // Separar planeta natal
    public void homeworldSetUp(String line) {

        int homeworldBegin = line.indexOf("'homeworld'");
        int homeworldEnd = line.indexOf("'films'", homeworldBegin);
        String homeworld = line.substring(homeworldBegin + 14, homeworldEnd).trim().replace(",", "").replace("'", "");

        this.homeWorld = homeworld;
        // MyIO.println("##" + homeworld);
    }
}

class ListaSequencial {
    private Characters personagem[];
    private int contador;

    public ListaSequencial() {
        personagem = new Characters[1000];
        contador = 0;
    }

    public ListaSequencial(int size) {
        personagem = new Characters[size];
        contador = 0;
    }

    // public int getSize() {
    // return size;
    // }

    public void inserirInicio(Characters jedi) {
        for (int i = contador; i > 0; i--) {
            personagem[i] = personagem[i - 1];
        }
        personagem[0] = jedi;
        contador++;
    }

    public void inserirFinal(Characters jedi) {
        personagem[contador++] = jedi;
    }

    public void inserir(Characters jedi, int posicao) {
        for (int i = contador; i > posicao; i--) {
            personagem[i] = personagem[i - 1];
        }
        personagem[posicao] = jedi;
        contador++;
    }

    public Characters removerInicio() {
        Characters aux = personagem[0];
        contador--;

        for (int i = 0; i < contador; i++) {
            personagem[i] = personagem[i + 1];
        }
        return aux;
    }

    public Characters removerFinal() {
        return personagem[--contador];
    }

    public Characters remover(int posicao) {
        Characters aux = personagem[posicao];
        contador--;

        for (int i = posicao; i < contador; i++) {
            personagem[i] = personagem[i + 1];
        }
        return aux;
    }

    /*
     * public void escrever() throws Exception { for (int i = 0; i < contador; i++)
     * { MyIO.print("[" + i + "] ## "); // personagem[i].readCharacter();
     * MyIO.print(personagem[i].getName() + " ## ");
     * MyIO.print(personagem[i].getHeight() + " ## ");
     * MyIO.print(personagem[i].getWeight() + " ## ");
     * MyIO.print(personagem[i].getHairColor() + " ## ");
     * MyIO.print(personagem[i].getSkinColor() + " ## ");
     * MyIO.print(personagem[i].getEyeColor() + " ## ");
     * MyIO.print(personagem[i].getBirthYear() + " ## ");
     * MyIO.print(personagem[i].getGenre() + " ## ");
     * MyIO.println(personagem[i].getHomeWorld() + " ## "); } }
     */
    public void ordenar() throws Exception {

        mergeSort(personagem, contador);

        for (int i = 0; i < contador; i++) {
            // MyIO.print("[" + i + "] ## ");
            // personagem[i].readCharacter();
            MyIO.print(personagem[i].getName() + " ## ");
            MyIO.print(personagem[i].getHeight() + " ## ");
            MyIO.print(personagem[i].getWeight() + " ## ");
            MyIO.print(personagem[i].getHairColor() + " ## ");
            MyIO.print(personagem[i].getSkinColor() + " ## ");
            MyIO.print(personagem[i].getEyeColor() + " ## ");
            MyIO.print(personagem[i].getBirthYear() + " ## ");
            MyIO.print(personagem[i].getGenre() + " ## ");
            MyIO.println(personagem[i].getHomeWorld() + " ## ");
        }
    }

    public void mergeSort(Characters[] jedi, int numero) throws Exception {

        if (numero < 2) {

            return;
        }
        int middle = numero / 2;
        Characters[] left = new Characters[middle];
        Characters[] right = new Characters[numero - middle];

        for (int i = 0; i < middle; i++) {

            left[i] = jedi[i];
        }
        for (int i = middle; i < numero; i++) {

            right[i - middle] = jedi[i];
        }
        mergeSort(left, middle);
        mergeSort(right, numero - middle);

        justMerge(jedi, left, right, middle, numero - middle);
    }

    public void justMerge(Characters[] jedi, Characters[] left, Characters[] right, int esquerda, int direita)
            throws Exception {

        int i = 0, j = 0, k = 0;

        while (i < esquerda && j < direita) {

            if (left[i].getHomeWorld().compareTo(right[j].getHomeWorld()) <= 0) {

                jedi[k++] = left[i++];
            } else {

                jedi[k++] = right[j++];
            }
        }

        while (i < esquerda) {

            jedi[k++] = left[i++];
        }

        while (j < direita) {

            jedi[k++] = right[j++];
        }
    }
}

class MergeSort {
    // Identificar palavra FIM
    public static boolean isFIM(String input) {
        return (input.length() >= 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M');
    }

    public static String ISO88591toUTF8(String strISO) {
        try {
            byte[] isoBytes = strISO.getBytes("ISO-8859-1");
            return new String(isoBytes, "UTF-8");
        } catch (IOException e) {
        }
        return strISO;
    }

    /*
     * public static void lerComando(String comando, ListaSequencial list) {
     * 
     * int posicao = 0; Characters character2 = new Characters(); String comand[] =
     * comando.split(" ");
     * 
     * switch (comand[0]) { case "II": character2.readCharacter(comand[1]);
     * list.inserirInicio(character2); break; case "IF":
     * character2.readCharacter(comand[1]); list.inserirFinal(character2); break;
     * case "I*": posicao = Integer.parseInt(comand[1]);
     * character2.readCharacter(comand[2]); list.inserir(character2, posicao);
     * break; case "R*": posicao = Integer.parseInt(comand[1]); character2 =
     * list.remover(posicao); MyIO.println("(R) " + character2.getName()); break;
     * case "RI": character2 = list.removerInicio(); MyIO.println("(R) " +
     * character2.getName()); break; case "RF": character2 = list.removerFinal();
     * MyIO.println("(R) " + character2.getName()); break; } }
     */

    public static void main(String[] args) throws Exception {
        // Inicializacao de lista
        ListaSequencial list = new ListaSequencial();
        // Array de characters
        Characters[] character = new Characters[100];

        String[] input = new String[700];
        int enterNumber = 0;

        // MyIO.setCharset("UTF-8");

        // Leitura de dados
        do {
            input[enterNumber] = MyIO.readLine();
            input[enterNumber] = ISO88591toUTF8(input[enterNumber]);
        } while (isFIM(input[enterNumber++]) == false);
        enterNumber--;

        for (int i = 0; i < enterNumber; i++) {
            character[i] = new Characters();
            character[i].readCharacter(input[i]);
            list.inserirFinal(character[i]);
        }
        /*
         * int size = Integer.parseInt(MyIO.readLine());
         * 
         * String[] comand = new String[1000];
         * 
         * for (int i = 0; i < size; i++) { comand[i] = MyIO.readLine();
         * lerComando(comand[i], list); } list.escrever();
         */

        list.ordenar();
    }
}