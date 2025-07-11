/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao_medicamentos;

import java.time.LocalTime;

/**
 *
 * @author menes
 */
public class MedicamentoControlado extends Medicamento{
    private String observacaoImportante;
    
    public MedicamentoControlado(String nomeMedicamento,String nomePaciente, String dosagem, LocalTime ultimaDose, int frequencia, int duracaoTratamento){
        super(nomeMedicamento,nomePaciente, dosagem, ultimaDose, frequencia, duracaoTratamento);
        this.observacaoImportante=observacaoImportante;
    }

    public String getObservacaoImportante() {
        return observacaoImportante;
    }

    public void setObservacaoImportante(String observacaoImportante) {
        this.observacaoImportante = observacaoImportante;
    }
    
    // Método sobrescrito.
    @Override
    public void informacoesMedicamento() {
        super.informacoesMedicamento(); 
    }
    
    // Método abstrato sobrescrito.
    @Override
    void observacao() {
        System.out.println("A observação importante sobre o paciênte ou medicamento que você adicionou: "+observacaoImportante);
    }
    
    
}
