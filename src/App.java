import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        File arquivo = new File("load\\save.txt");
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        exibeMenu();
    }

    public static String lerTarefas() throws IOException {

        Path caminho = Paths.get("load\\save.txt");
        List<String> lst = Files.readAllLines(caminho, StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        int count = 1;

        if (lst.isEmpty()) {
            return "Não há tarefas";
        } else {
            for (String string : lst) {
                sb.append(count + " - " + string + "\n");
                count++;
            }

            return sb.toString();
        }
    }

    public static List<String> lerTarefasAdicionar() throws IOException {

        Path caminho = Paths.get("load\\save.txt");
        List<String> lst = Files.readAllLines(caminho, StandardCharsets.UTF_8);
        return lst;
    }

    public static List<String> lerTarefasConclusao() throws IOException {

        Path caminho = Paths.get("load\\save.txt");
        List<String> lst = Files.readAllLines(caminho, StandardCharsets.UTF_8);

        return lst;
    }

    public static void limparTela() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void exibeMenu() throws InterruptedException, IOException {
        limparTela();
        System.out.println("Bem-vindo ao To Do List");
        System.out.println("1 - Verificar tarefas \n2 - Adicionar Tarefa \n3- Concluir Tarefa \n4 - Sair");

        int respostaMenu = 0;

        try {
            respostaMenu = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("Digite uma opção válida");
        }

        switch (respostaMenu) {

            case 1:
                System.out.println(lerTarefas());
                System.console().readLine();
                exibeMenu();

            case 2:
                System.out.println("Qual tarefa deseja adicionar?");
                try {
                    adicionaTarefa(System.console().readLine());
                    System.out.println("Tarefa adicionada com sucesso");
                    System.console().readLine();
                    exibeMenu();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            case 3:
                try {
                    concluirTarefa();
                    System.out.println("Tarefa concluída com êxito");
                    System.console().readLine();
                    exibeMenu();
                } catch (Exception e) {
                    System.out.println("Escolha um ID válido");
                    exibeMenu();
                }

            case 4:
                System.exit(0);
            default:

        }
    }

    public static void adicionaTarefa(String tarefa) throws IOException {

        List<String> tarefaSalva = lerTarefasAdicionar();
        tarefaSalva.add(tarefa);
        StringBuilder sb = new StringBuilder();
        FileWriter writer = new FileWriter("load\\save.txt");

        for (String string : tarefaSalva) {
            sb.append(string + "\n");
        }

        writer.write(sb.toString());
        writer.close();
    }

    public static void concluirTarefa() throws IOException {

        List<String> listaTarefas = lerTarefasConclusao();
        String exibe = lerTarefas();
        System.out.println(exibe);

        StringBuilder sb = new StringBuilder();

        System.out.println("Digite o id da tarefa que deseja concluir");
        int resposta = 0;

        try {
            resposta = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("Digite uma opção válida");
        }

        FileWriter writer = new FileWriter("load\\save.txt");
        listaTarefas.remove((resposta - 1));

        for (String string : listaTarefas) {
            sb.append(string + "\n");
        }

        writer.write(sb.toString());
        writer.close();
    }
}
