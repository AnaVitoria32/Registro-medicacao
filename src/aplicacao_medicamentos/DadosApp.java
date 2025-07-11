/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacao_medicamentos;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author menes
 */
public class DadosApp {
    public static List<Medicamento> listaTodosMedicamentos= new ArrayList<>();
    
    public static List<Medicamento> carregarTodosMedicamentosBanco(){
        List <Medicamento> lista= new ArrayList();
        
        try(Connection coon= ConexaoBD.conectar()){
            // Medicamento comum.
            String sqlComuns= "SELECT * FROM medicamento_comum";
            PreparedStatement stmt1= coon.prepareStatement(sqlComuns);
            ResultSet rs1= stmt1.executeQuery();
            
            while(rs1.next()){
                String nomePaciente= rs1.getString("nome_paciente");
                String nomeMedicamento=rs1.getString("nome_medicamento");
                String dosagem=rs1.getString("dosagem");
                LocalTime ultimaDose=rs1.getTime("ultima_dose").toLocalTime();
                int frequencia=rs1.getInt("frequencia");
                int duracao=rs1.getInt("duracao");
                
                MedicamentoComum m=new MedicamentoComum(nomePaciente, nomeMedicamento, dosagem,ultimaDose, frequencia, duracao);
                lista.add(m);
            }
            
            //Medicamento controlado.
            String sqlControlados="SELECT * FROM medicamento_controlado";
            PreparedStatement stmt2=coon.prepareStatement(sqlControlados);
            ResultSet rs2= stmt2.executeQuery();
            
            while(rs2.next()){
                String nomePaciente= rs2.getString("nome_paciente");
                String nomeMedicamento=rs2.getString("nome_medicamento");
                String dosagem=rs2.getString("dosagem");
                LocalTime ultimaDose=rs2.getTime("ultima_dose").toLocalTime();
                int frequencia=rs2.getInt("frequencia");
                int duracao=rs2.getInt("duracao");
                
                MedicamentoControlado medi=new MedicamentoControlado(nomePaciente, nomeMedicamento, dosagem, ultimaDose, frequencia, duracao);
                lista.add(medi);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
        return lista;
    }
    
    public static void atualizarMedicamentoComum(MedicamentoComum m) {
      String sql = "UPDATE medicamento_comum SET nome_paciente = ?, nome_medicamento = ?, dosagem = ?, ultima_dose = ?, frequencia = ?, duracao = ? WHERE id = ?";

      try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setString(1, m.getNomePaciente());
         stmt.setString(2, m.getNomeMedicamento());
         stmt.setString(3, m.getDosagem());
         stmt.setTime(4, java.sql.Time.valueOf(m.getUltimaDose()));
         stmt.setInt(5, m.getFrequencia());
         stmt.setInt(6, m.getDuracaoTratamento());
        

         stmt.executeUpdate();

        } catch (Exception e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "Erro ao atualizar medicamento: " + e.getMessage());
        }
    }
    
    public static void atualizarMedicamentoControlado(MedicamentoControlado m) {
      String sql = "UPDATE medicamento_controlado SET nome_paciente = ?, nome_medicamento = ?, dosagem = ?, ultima_dose = ?, frequencia = ?, duracao = ? WHERE id = ?";

      try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

         stmt.setString(1, m.getNomePaciente());
         stmt.setString(2, m.getNomeMedicamento());
         stmt.setString(3, m.getDosagem());
         stmt.setTime(4, java.sql.Time.valueOf(m.getUltimaDose()));
         stmt.setInt(5, m.getFrequencia());
         stmt.setInt(6, m.getDuracaoTratamento());
        

         stmt.executeUpdate();

        } catch (Exception e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, "Erro ao atualizar medicamento controlado: " + e.getMessage());
        }
    }
}


