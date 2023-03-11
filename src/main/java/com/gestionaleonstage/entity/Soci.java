package com.gestionaleonstage.entity;



/**
 * Classe per rappresentare l'entità Socio
 */
public class Soci implements Comparable<Soci>{
   private int tessera;
   private String dataIscrizione;
   private String dataApprovazione;
   private String cognome;
   private String nome;
   private String nascita;
   private String luogoNascita;
   private String via;
   private String citta;
   private String cap;
   private String telefono;
   private String provincia;
   private String email;
   private String dataAnnullamento;
   private String consenso;
   private String minorenne;
   private String note;

   private int ruolo;


   public Soci(){}

   public Soci(int tessera, String dataIscrizione, String dataApprovazione, String cognome, String nome, String nascita, String luogoNascita, String via, String citta, String cap, String telefono, String provincia, String email, String dataAnnullamento,int ruolo, String consenso, String minorenne, String note) {
      this.tessera = tessera;
      this.dataIscrizione = dataIscrizione;
      this.dataApprovazione = dataApprovazione;
      this.cognome = cognome;
      this.nome = nome;
      this.nascita = nascita;
      this.luogoNascita = luogoNascita;
      this.via = via;
      this.citta = citta;
      this.cap = cap;
      this.telefono = telefono;
      this.provincia = provincia;
      this.email = email;
      this.dataAnnullamento = dataAnnullamento;
      this.consenso = consenso;
      this.minorenne = minorenne;
      this.note = note;
      this.ruolo = ruolo;
   }

   public int getTessera() {
      return tessera;
   }

   public void setTessera(int tessera) {
      this.tessera = tessera;
   }

   public String getDataIscrizione() {
      return dataIscrizione;
   }

   public void setDataIscrizione(String dataIscrizione) {
      this.dataIscrizione = dataIscrizione;
   }

   public String getDataApprovazione() {
      return dataApprovazione;
   }

   public void setDataApprovazione(String dataApprovazione) {
      this.dataApprovazione = dataApprovazione;
   }

   public String getCognome() {
      return cognome;
   }

   public void setCognome(String cognome) {
      this.cognome = cognome;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getNascita() {
      return nascita;
   }

   public void setNascita(String nascita) {
      this.nascita = nascita;
   }

   public String getLuogoNascita() {
      return luogoNascita;
   }

   public void setLuogoNascita(String luogoNascita) {
      this.luogoNascita = luogoNascita;
   }

   public String getVia() {
      return via;
   }

   public void setVia(String via) {
      this.via = via;
   }

   public String getCitta() {
      return citta;
   }

   public void setCitta(String citta) {
      this.citta = citta;
   }

   public String getCap() {
      return cap;
   }

   public void setCap(String cap) {
      this.cap = cap;
   }

   public String getTelefono() {
      return telefono;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   public String getProvincia() {
      return provincia;
   }

   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getdataAnnullamento() {
      return dataAnnullamento;
   }

   public void setdataAnnullamento(String dataAnnullamento) {
      this.dataAnnullamento = dataAnnullamento;
   }

   public String getConsenso() {
      return consenso;
   }

   public void setConsenso(String consenso) {
      this.consenso = consenso;
   }

   public String getMinorenne() {
      return minorenne;
   }

   public void setMinorenne(String minorenne) {
      this.minorenne = minorenne;
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }
   public String getDataAnnullamento() {
      return dataAnnullamento;
   }

   public void setDataAnnullamento(String dataAnnullamento) {
      this.dataAnnullamento = dataAnnullamento;
   }

   public int getRuolo() {
      return ruolo;
   }

   public void setRuolo(int ruolo) {
      this.ruolo = ruolo;
   }

   /**
    * Override del toString per la visualizzazione di alcuni dati degli oggetti di tipo Soci
    * @return String
    */
   public String toString(){

      String x="Tessera: "+this.tessera+" Data Iscrizione: "+this.dataIscrizione +" Data Approvazione: "+this.dataApprovazione +" Nome: "+this.nome+" Cognome: "+this.cognome+ " Data Nascita: "+this.nascita+" Luogo nascita: "+this.luogoNascita +" Indirizzo: "+ this.via +" Citta: "+this.citta
              +" CAP: "+this.cap+ " Telefono: "+this.telefono +" Provincia: "+this.provincia+" Email: "+email+" DataAnnullamento: "+this.dataAnnullamento+" Consenso: "+this.consenso+" Minorenne: "+this.minorenne+" Note: "+this.note;
      return x;
   }

   /**
    * Funzione che compara due oggetti di tipo Soci per stabilire il maggiore e il minore
    * @param o oggetto da comparare
    * @return int -1 se l'oggetto in input è maggiore, 0 se sono uguali oppure 1 se l'oggetto corrente è maggiore
    */
   @Override
   public int compareTo(Soci o) {
      if(this.nome.compareTo(o.getNome()) <0){
         return -1;
      } else if (this.nome.compareTo(o.getNome())>0) {
         return 1;
      }
      return 0;
   }
}