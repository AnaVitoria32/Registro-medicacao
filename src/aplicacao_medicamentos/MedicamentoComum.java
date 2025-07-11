/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao_medicamentos;

import java.time.LocalTime;

public class MedicamentoComum extends Medicamento{
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public MedicamentoComum(String nomeMedicamento,String nomePaciente, String dosagem, LocalTime ultimaDose, int frequencia, int duracaoTratamento){
        super(nomeMedicamento,nomePaciente, dosagem, ultimaDose, frequencia, duracaoTratamento);
    }
    
    // Método sobrescrito.
    @Override
    public void informacoesMedicamento() {
        super.informacoesMedicamento(); 
    }
    
    // Método abstrato sobrescrito.
    @Override
    void observacao() {
        System.out.println("A observação que você adicionou sobre o medicamento ou paciente: "+observacao);
    }
    
    
}
