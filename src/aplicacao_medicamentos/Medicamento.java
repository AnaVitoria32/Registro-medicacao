/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao_medicamentos;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author menes
 */
abstract class Medicamento {
    private String nomeMedicamento, nomePaciente;
    private String dosagem; //Sempre em mg.
    private LocalTime ultimaDose, proxima; // Utilizaremos para calcular o horário da próxima dose.
    private int frequencia, duracaoTratamento;

    public Medicamento() {
    }

    public Medicamento(String nomeMedicamento,String nomePaciente, String dosagem, LocalTime ultimaDose, int frequencia, int duracaoTratamento) {
        this.nomeMedicamento = nomeMedicamento;
        this.nomePaciente=nomePaciente;
        this.dosagem = dosagem;
        this.ultimaDose = ultimaDose;
        this.frequencia = frequencia;
        this.duracaoTratamento = duracaoTratamento;
        this.proxima= proxima;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public LocalTime getUltimaDose() {
        return ultimaDose;
    }

    public void setUltimaDose(LocalTime ultimaDose) {
        this.ultimaDose = ultimaDose;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public int getDuracaoTratamento() {
        return duracaoTratamento;
    }

    public void setDuracaoTratamento(int duracaoTratamento) {
        this.duracaoTratamento = duracaoTratamento;
    }

    public LocalTime getProxima() {
        return proxima;
    }

    public void setProxima(LocalTime proxima) {
        this.proxima = proxima;
    }
    
    
    // Criando o método que guarda a informção do último horário em que a dose do remédio foi ministrada.
    public void ultimaDoseMinistrada(String horario){
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("HH:mm");
        ultimaDose= LocalTime.parse(horario, formatter); // Vai pegar o horário informado e converter para HH:mm.
    }
    
    // Criando o método que calcula o próximo horário que o remédio deve ser ministrado.
    public LocalTime proximaDoseMinistrada(){
        if (ultimaDose == null) {
        System.out.println("A última dose não foi registrada! Por favor, faça o registro!");
          return null;
        }

       // Calcula o próximo horário
        LocalTime proxima = ultimaDose.plusHours(frequencia);

       // Calcula o tempo restante até a próxima dose
       Duration tempoRestante = Duration.between(LocalTime.now(), proxima);

       System.out.println("A próxima dose deve ser dada às: " + proxima);

       if (tempoRestante.isNegative()) {
          System.out.println("Já passou do horário da dose! Por favor, insira o novo horário da dose!");
       } else {
         System.out.println("Faltam: " + tempoRestante.toHours() + "h e " +
                           (tempoRestante.toMinutes() % 60) + "min para a próxima dose!");
        }
      return proxima;
    }
    
    // Método para sobrescrita, vai mostrar as informações de cada medicamento.
    public void informacoesMedicamento(){
        System.out.println(nomeMedicamento+",nome do paciente:"+nomePaciente+", dosagem: "+dosagem+", horário da última dose:"+ultimaDose+
                ",frequencia do medicamento: "+frequencia+" e duração do tratamento: "+duracaoTratamento);
    }
    
    // Método abstrato.
    abstract void observacao();
}
