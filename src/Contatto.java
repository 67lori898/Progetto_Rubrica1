enum tipoContratto{abitazione, cellulare, aziendale};
public class Contatto {



        public String nome;
        public String cognome;
        public String telefono;
        public tipoContratto tipo;
        public boolean contactHidden;

        public double saldoTelefonico;


        //Istanziamo il costruttore:
        // Costruttore parametrizzato
        public Contatto() {
            this.nome = nome;
            this.cognome = cognome;
            this.telefono = telefono;
            this.contactHidden = false; // Per default, il contatto è visibile
          //  this.saldoTelefonico = 0.0;
        }
    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public String getSurname() {
        return cognome;
    }

    public void setSurname(String surname) {
        this.cognome = cognome;
    }


    public String stampa()
        {
            return String.format("Nome: %s Cognome: %s Telefono: %s, tipo: %s", nome, cognome, telefono, tipo.toString());
        }
        //file di testo è un file che contiene al suo interno delle stringhe:
        //Riscrivere un metodo(stesso nome+)
        //Metodo di istanza è un metodo definito sulla variabile
        //Metodo statico
        @Override
        public String toString(){
            //Adatta le stringhe al formato csv
            return String.format("%s,%s,%s,%s",nome,cognome,telefono,tipo.toString());


        }

        //This method can compare the two string(if you order by name, thbis method compare the name)

        /*
         * @param myString
         *
         * */


        //*public static int compareTo(String cognome, String nome){
         /* if(cognome.compareTo(no))







    }*/



}




