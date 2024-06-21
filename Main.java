import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();

        // array pra inserir os funcionarios na ordem listada
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // remove o funcionario João
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // comando pra imprimir os funcionarios e as infos

            System.out.println("Funcionários da Empresa: ");
            System.out.println();
      
      
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.formatarDataNascimento());
            System.out.println("Salário: " + funcionario.formatarSalario());
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
            
        }

        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println();

        // comando para dar os merecidos 10% de aumento para os funcionários (CLT VENCEU!)
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        }

        // não conhecia bem a estrutura "MAP" e acabei ficando sem tempo pra aprender e tentar aplicar no código, então acabei pulando os itens 3.5 e 3.6.


        //  imprime os aniversariantes dos meses 10 e 12
        System.out.println("\nAniversariantes dos meses 10 e 12:");
        System.out.println();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().getMonth() == Month.OCTOBER || funcionario.getDataNascimento().getMonth() == Month.DECEMBER) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.formatarDataNascimento());
            }
        }
      

        // imprime os funcionários com maior idade
      Funcionario funcionarioMaisVelho = funcionarios.stream()
        .min(Comparator.comparing(funcionario -> funcionario.getDataNascimento()))
        .orElseThrow(NoSuchElementException::new);
      int idadeMaisVelho = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println();
      
      System.out.println("\nFuncionário com maior idade: " + funcionarioMaisVelho.getNome() + " | Idade: " + idadeMaisVelho);

        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println();

        // imprime a lisa de funcionarios em orfem alfabetica
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários em ordem alfabética:");
        System.out.println();
        for (Funcionario funcionario : funcionariosOrdenados) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.formatarDataNascimento());
            System.out.println("Salário: " + funcionario.formatarSalario());
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }

        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println();

        // total de salarios
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + totalSalarios);

        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println();

        // quantos salarios minimos (desatualizado) cada um ganha 
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantos salários mínimos cada funcionário ganha:");
        System.out.println();
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salários Mínimos: " + salariosMinimos);
            System.out.println();
        }
    }
}