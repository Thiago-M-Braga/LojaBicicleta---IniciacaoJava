import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Produto> carrinho = new ArrayList<>();
    private static ArrayList<Produto> listaProdutos = new ArrayList<>();
    private static int quantidadeVendas = 0;
    private static double valorTotalVendas = 0;

    public static void main(String[] args) {
        login();
    }

    private static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-= LOGIN =-=-=-=-=");
        System.out.println("Bem-vindo ao autoatendimento da bicicletaria XPTO Bikes!");
        System.out.println("Insira seu nome");
        String nome = sc.nextLine();
        if (nome.equals("xptorestrito")) {
            menuRestrito();
        } else if (nome.equals("a")) {
            System.out.println("=-=-=-=-= FINALIZAÇÃO =-=-=-=-=");
            System.out.println("Quantidade de vendas: " + quantidadeVendas + "\nValor total de vendas: R$" + valorTotalVendas);
            System.out.println("Obrigado por comprar na XPTO Bikes!");
        } else {
            menu();
        }
    }

    private static void menuRestrito() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-= MENU RESTRITO =-=-=-=-=");
        System.out.println("Qual o nome do cliente? ");
        String nome = sc.nextLine();
        System.out.println("Qual o valor total? ");
        double valorTotal = sc.nextDouble();
        finalizarCompra("adm", valorTotal);
        System.out.println("Deseja continuar no menu restrito? \n1 - Sim \n2 - Não");
        int opcao = sc.nextInt();
        if (opcao == 1) {
            menuRestrito();
        } else {
            login();
        }
    }

    private static void menu() {
        Scanner sc = new Scanner(System.in);
        cadastroProdutos();
        System.out.println("=-=-=-=-= MENU =-=-=-=-=");
        System.out.println("1 - Ver promoções \n2 - Solicitar serviço de manutenção \n3 - Listar carrinho de compra \n4 - Finalizar carrinho de compra \n5 - Sair \nDigite a opção desejada:");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                promocoes();
                break;
            case 2:
                servicoManutencao();
                break;
            case 3:
                listarCarrinho();
                break;
            case 4:
                finalizarCompra("user", 0.0);
                break;
            case 5:
                login();
                break;
            default:
                System.out.println("Opção inválida! Tu é burro?!");
                break;
        }
    }

    private static void finalizarCompra(String tipoUsuario, double valorTotal) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-= FINALIZAR COMPRA =-=-=-=-=");
        if (carrinho.size() <= 0 && tipoUsuario != "adm") {
            System.out.println("Carrinho vazio! Adicione produtos ao carrinho antes de finalizar a compra!");
        } else {
            System.out.println("Qual a forma de pagamento? \nD - Dinheiro \nC - Cartão \nV - Voltar");
            char opcao = sc.next().charAt(0);
            if (tipoUsuario.equals("user")) {
                valorTotal = calculaValorTotal();
            }
            switch (opcao) {
                case 'D', 'd':
                    System.out.println("Valor total: R$" + (valorTotal - ((valorTotal * 10) / 100)));
                    valorTotalVendas += (valorTotal - ((valorTotal * 10) / 100));
                    quantidadeVendas++;
                    System.out.println("Compra finalizada com sucesso!");
                    carrinho.removeAll(carrinho);
                    break;
                case 'C', 'c':
                    System.out.println("Valor total: R$" + valorTotal);
                    valorTotalVendas += valorTotal;
                    quantidadeVendas++;
                    System.out.println("Compra finalizada com sucesso!");
                    carrinho.removeAll(carrinho);
                    break;
                case 'V', 'v':
                    break;
                default:
                    System.out.println("Opção inválida! Tu é burro?!");
                    break;
            }
        }
        if (tipoUsuario.equals("user")) {
            menu();
        } else {
        }
    }

    private static double calculaValorTotal() {
        double valorTotal = 0;
        for (Produto produto : carrinho) {
            valorTotal += produto.getValor();
        }
        return valorTotal;
    }

    private static void listarCarrinho() {
        System.out.println("=-=-=-=-= CARRINHO =-=-=-=-=");
        if (carrinho.size() <= 0) {
            System.out.println("Carrinho vazio! Adicione produtos para vizualizá-los!\n");
        } else {
            for (int i = 0; i < carrinho.size(); i++) {
                System.out.println(carrinho.get(i));
            }
        }
        menu();
    }

    private static void servicoManutencao() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-= SERVIÇO DE MANUTENÇÃO =-=-=-=-=");
        System.out.println("Código 201 - Troca de pneu - R$ 55,99 " +
                "\nCódigo 202 - Lavagem completa -R$ 12,99 " +
                "\nCódigo 203 - Freio - R$ 10,99 \n\nDeseja; \n1 - Adicionar ao carrinho de compras \n2 - Voltar");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Digite o código do serviço desejado: ");
                int codigo = sc.nextInt();
                addProdutoNoCarrinho(codigo);
                break;
            case 2:
                break;
            default:
                System.out.println("Opção inválida! Tu é burro?!");
                break;
        }
        menu();
    }

    private static void addProdutoNoCarrinho(int codigo) {
        for (int i = 0; i < listaProdutos.size(); i++) {
            if (listaProdutos.get(i).getCodigo() == codigo) {
                if (carrinho.size() == 3) {
                    System.out.println("Carrinho cheio!");
                    break;
                } else {
                    carrinho.add(listaProdutos.get(i));
                    System.out.println("Produto adicionado ao carrinho!");
                    break;
                }
            }
        }
    }

    private static void promocoes() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=-=-=-=-= PROMOÇÕES =-=-=-=-=");
        System.out.println("Código 101 - Bicicleta nova na cor amarela, aro 26, com 18 marchas e na promoção pelo preço de R$ 999.99. " +
                "\nCódigo 102 - Bicicleta usada na cor azul, aro 26, com 18 marchas e com o valor promocional de R$ 400,00. " +
                "\nCódigo 103 - Capacete de proteção por R$59.99. " +
                "\nCódigo 104 - Freio a disco por R$ 89,99. " +
                "\n\nDeseja; \n1 - Adicionar algum produto ao carrinho de compras " +
                "\n2 - Voltar");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Digite o código do produto desejado: ");
                int codigo = sc.nextInt();
                addProdutoNoCarrinho(codigo);
                break;
            case 2:
                break;
            default:
                System.out.println("Opção inválida! Tu é burro?!");
                break;
        }
        menu();
    }

    private static void cadastroProdutos() {
        listaProdutos.add(new Produto(101, "Bicicleta amarela, aro 26, 18 marchas", 999.99));
        listaProdutos.add(new Produto(102, "Bicicleta usada na cor azul, aro 26, 18 marchas", 400.00));
        listaProdutos.add(new Produto(103, "Capacete de proteção", 59.99));
        listaProdutos.add(new Produto(105, "Freio a disco", 89.99));
        listaProdutos.add(new Produto(201, "Troca de pneu", 55.99));
        listaProdutos.add(new Produto(202, "Lavagem completa", 12.99));
        listaProdutos.add(new Produto(203, "Freio", 10.99));
    }
}

