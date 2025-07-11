/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacao_medicamentos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author menes
 */
public class Principal {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        
        // ArrayList para guardar as inforações que serão registradas.
        ArrayList<MedicamentoComum> listaMedicamentoComum=new ArrayList();
        ArrayList<MedicamentoControlado> listaMedicamentoControlado=new ArrayList();
        
        //Início do menu para cadastro.
        while(true){
            System.out.println("Selecione o a opção que deseja!");
            System.out.println("MENU!");
            System.out.println("1. Cadastrar medicamento:");
            System.out.println("2. Registrar última dose:");
            System.out.println("3. Calcular próxima dose:");
            System.out.println("4. Ver informações de medicamentos:");
            System.out.println("5. Sair");
            
            int numeroOpcao;
            // Guarda o número que a pessoa selecionar e valida se pode ser usado ou se terá algum erro.
            try{
                numeroOpcao=Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("A opção desejada não existe!");
                continue;
            }
            
            // Vai entrar na opção desejada pelo usuário e reali´zará o que foi pedido.
            switch(numeroOpcao){
                case 1:
                    // Tratamento de exceções e cadastro dos dados do medicamento.
                    try{
                        // Vai realizar a pergunta para poder adicionar os dados nos lugares certos.
                        System.out.println("Digite o número do medicaemnto que deseja adicionar:");
                        System.out.println("1-Medicamento Comum.");
                        System.out.println("2-Medicaemnto Controlado.");
                        int tipoMedicamento=Integer.parseInt(scanner.nextLine());
                        
                        int controlado=1;
                        int comum=2;
                        if(tipoMedicamento !=comum && tipoMedicamento !=controlado){
                            System.out.println("O número digitado não está entre os que forma mostrados!");
                            break;
                        }
                        System.out.println("Digite o nome do medicamento que será adicionado:");
                        String nomeMedicamento=scanner.nextLine();
                        
                        System.out.println("Digite o nome do paciente que receberá o medicamento:");
                        String nomePaciente=scanner.nextLine();
                        
                        System.out.println("Digite a dosagem do medicamento(mg):");
                        String dosagem=scanner.nextLine();
                        
                        System.out.println("Digite o horário da última dose(HH:mm):");
                        String ultimoHorario=scanner.nextLine();
                        LocalTime horario= LocalTime.parse(ultimoHorario,DateTimeFormatter.ofPattern("HH:mm"));
                        
                        System.out.print("Digite a frêquencia da medicação (em horas): ");
                        int frequencia = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite a duração do tratamento que o paciente fará (em dias): ");
                        int duracaoTratamento = Integer.parseInt(scanner.nextLine());
                        
                        // Para saber em qual lista as informações do remédio será guardada.
                        if(tipoMedicamento==1){
                            MedicamentoComum medicamentoComum=new MedicamentoComum(nomeMedicamento, nomePaciente, 
                            dosagem, horario, frequencia, duracaoTratamento);
                            System.out.println("O medicamento comum foi cadastrado com SUCESSO!");
                        }else{
                            MedicamentoControlado medicamentoControlado=new MedicamentoControlado(nomeMedicamento,nomePaciente,
                            dosagem, horario, frequencia, duracaoTratamento);
                            System.out.println("O medicamento controlado foi cadastrado com SUCESSO!");
                        }
                    }catch(DateTimeParseException e){
                        //Tratamneto de exceção caso a entrada das horas estiver diferente de (HH:mm).
                        System.out.println("As horas não foram digitadas corretamente(HH:mm), por favor, digite novamente!");
                    }catch(NumberFormatException e){
                        //Tratamento de exceção caso o número digitado não esteja entre os que foram disponibilizados.
                        System.out.println("O número digitado está incorreto!");
                    }catch(Exception e){
                        //Caso algum erro totalmente inesperado aconteça.
                        System.out.println("Erro inseperado ao cadastrar o medicamento!");
                    }
                break;
                
                case 2:
                    // Tratamento de exceções e registro da última dose do paciente. Caso ele queira recadastrar a informação.
                    try{
                      System.out.println("Qual medicamento deseja modificar? Sendo, 1- medicamento comum e 2- medicamento controlado.");
                      int tipoMedicamento2=Integer.parseInt(scanner.nextLine());  
                      
                      //Podemos identificar se a lista de medicaemnto comum está vazia.
                      if(tipoMedicamento2==1){
                          if(listaMedicamentoComum.isEmpty()){
                              System.out.println("Nenhum medicamento foi cadastrado como COMUM!");
                              break;
                            }
                          //Vai pegar o nome de todos os medicamentos registrados na lista COMUM.
                          for (int i = 0; i < listaMedicamentoComum.size(); i++) {
                             System.out.println((i + 1) + " - " + listaMedicamentoComum.get(i).getNomeMedicamento());
                            }
                          // Pede para a pessoa escolher o medicamento que deseja modificar.
                            System.out.print("Escolha o número do medicamento: ");
                            int indice = Integer.parseInt(scanner.nextLine()) - 1;

                            System.out.print("Digite o novo horário da última dose (HH:mm): ");
                            String novoHorario = scanner.nextLine();
                            //Vai atualizar o novo horário, utilizando o índice do medicamento escolhido.
                            listaMedicamentoComum.get(indice).ultimaDoseMinistrada(novoHorario);
                            System.out.println("Horário atualizado com sucesso.");    
                        }else{
                          if(tipoMedicamento2==2){
                              if(listaMedicamentoComum.isEmpty()){
                                 System.out.println("Nenhum medicamento foi cadastrado como CONTROLADO!");
                                break; 
                              }
                              for (int i = 0; i < listaMedicamentoControlado.size(); i++) {
                                 System.out.println((i + 1) + " - " + listaMedicamentoControlado.get(i).getNomeMedicamento());
                                }
                              System.out.print("Escolha o número do medicamento: ");
                              int indice = Integer.parseInt(scanner.nextLine()) - 1;

                              System.out.print("Digite o novo horário da última dose (HH:mm): ");
                              String horario = scanner.nextLine();
                              listaMedicamentoControlado.get(indice).ultimaDoseMinistrada(horario);
                              System.out.println("Horário atualizado com sucesso.");
                           }else{
                              System.out.println("Tipo inválido!");
                            } 
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Valor numérico é inválido! Tente novamente.");
                    }catch(DateTimeParseException e){
                        System.out.println("Formato do horário está incorreto! Por favor, use HH:mm.");
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("O número do medicamento selecionado não faz parte da lista mostrada!");
                    }catch(Exception e){
                        System.out.println("Erro inesperado!");
                    }
                break;
                
                case 3:
                    // Tratamento de exceções e cálculo da próxima dose.
                    try{
                        System.out.println("Qual medicamento deseja saber a próxima dose? Sendo, 1- medicamento comum e 2- medicamento controlado.");
                        int tipoMedicamento3=Integer.parseInt(scanner.nextLine());
                        
                        if(tipoMedicamento3==1){
                            if(listaMedicamentoComum.isEmpty()){
                                System.out.println("Nenhum medicamento foi cadastrado como COMUM!");
                                break;
                            }
                            
                            for(int i=0; i<listaMedicamentoComum.size(); i++){
                                System.out.println((i + 1) + " - " + listaMedicamentoControlado.get(i).getNomeMedicamento());
                            }
                            
                            //Irá fazer o cálculo da próxima dose, pegando o índice do medicamento e utilizando os dados de última dose e a frequência da medicação.
                            System.out.println("Escolha o número do medicamento:");
                            int indice2=Integer.parseInt(scanner.nextLine());
                            listaMedicamentoComum.get(indice2).proximaDoseMinistrada();
                        }else{
                            if(tipoMedicamento3==2){
                                if(listaMedicamentoControlado.isEmpty()){
                                    System.out.println("Nenhum medicamento foi cadastrado como CONTROLADO!");
                                    break;
                                }
                                
                                for(int i=0; i<listaMedicamentoControlado.size(); i++){
                                    System.out.println((i + 1) + " - " + listaMedicamentoControlado.get(i).getNomeMedicamento());
                                }
                                
                                System.out.println("Escolha o número do medicamento:");
                                int indice3=Integer.parseInt(scanner.nextLine());
                                listaMedicamentoComum.get(indice3).proximaDoseMinistrada();
                            }else{
                                System.out.println("O tipo digitado não corresponde aos mostrados!");
                            }
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Valor numérico é inválido! Tente novamente.");
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("O número do medicamento selecionado não faz parte da lista mostrada!");
                    }catch(Exception e){
                        System.out.println("Erro inesperado!");
                    }
                break;
                
                case 4:
                    // Tratamento de exceções e apresentação dos medicamentos.
                    try{
                        System.out.println("Escolha quais medicaemntos deseja visualizar:");
                        System.out.println("1- Medicamento Comum.");
                        System.out.println("2- Medicamento Controlado.");
                        System.out.println("3- Todos os medicamentos.");
                        int escolha=Integer.parseInt(scanner.nextLine());
                        
                        // Apenas os medicamentos comuns serão mostrados.
                        if (escolha==1) {
                          if (listaMedicamentoComum.isEmpty()) {
                             System.out.println("Nenhum medicamento cadastrado como COMUM!");
                            }else{
                               for (int i=0; i<listaMedicamentoComum.size(); i++) {
                                  listaMedicamentoComum.get(i).informacoesMedicamento();
                                  listaMedicamentoComum.get(i).observacao();
                                }
                            }
                        }else{
                            // Apenas os medicamentos controlados serão mostrados.
                            if(escolha==2){
                                if(listaMedicamentoControlado.isEmpty()){
                                    System.out.println("Nenhum medicamento cadastrado como CONTROLADO!");
                                }else{
                                    for(int i=0; i<listaMedicamentoControlado.size(); i++){
                                        listaMedicamentoControlado.get(i).informacoesMedicamento();
                                        listaMedicamentoControlado.get(i).observacao();
                                    }
                                }
                            }else{
                                // Os medicaemntos comuns e controlados serão mostrados.
                                if(escolha==3){
                                    if(listaMedicamentoComum.isEmpty() && listaMedicamentoControlado.isEmpty()){
                                        System.out.println("Nenhum medicamento cadastrado!");
                                    }else{
                                        if (listaMedicamentoComum.isEmpty()) {
                                          System.out.println("Medicamentos Comuns:");
                                          for (int i=0; i<listaMedicamentoComum.size(); i++) {
                                             listaMedicamentoComum.get(i).informacoesMedicamento();
                                             listaMedicamentoComum.get(i).observacao();
                                            }
                                        }
                                        if (listaMedicamentoControlado.isEmpty()) {
                                          System.out.println("Medicamentos Controlados:");
                                          for (int i=0; i<listaMedicamentoControlado.size(); i++) {
                                             listaMedicamentoControlado.get(i).informacoesMedicamento();
                                             listaMedicamentoControlado.get(i).observacao();
                                            }
                                        }
                                    }
                                }else{
                                     System.out.println("O número escolhido naõ está dentro da lista mostrada!");
                                }
                            }
                        }
                        
                    }catch(NumberFormatException e){
                        System.out.println("Digite apenas um dos numéros informados!");
                    }catch(Exception e){
                        System.out.println("Erro inseperado ao monstrar os medicamentos!");
                    }
                break;
                // Aqui o programa será encerrado.
                case 5:
                    System.out.println("O programa está encerrando, agradecemos a sua escolha! Até mais.");
                    scanner.close();
                    return; 
                // Caso o número escolhido não esteja na lista o programa será encerrado.    
                default:
                    System.out.println("Opção não encontrada!");
                    
            break;
                            
            }
        }       
    }    
}
