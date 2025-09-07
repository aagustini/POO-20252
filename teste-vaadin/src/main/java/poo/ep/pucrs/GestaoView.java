package poo.ep.pucrs;


import java.util.Map;
import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.Route;

@Route("gestao")
public class GestaoView extends VerticalLayout {

    public GestaoView() {
        CadastroPessoas cadPessoas = CadastroPessoas.getInstance();

        setSpacing(true);
        setPadding(true);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(new H2("Gestão de Pessoas"));

        // Agrupar pessoas por país
        Map<String, Long> pessoasPorPais = cadPessoas.getLista().stream()
                .collect(Collectors.groupingBy(Pessoa::getPais, Collectors.counting()));

        // Calcular total para normalizar barras
        long max = pessoasPorPais.values().stream().mapToLong(Long::longValue).max().orElse(1);

        VerticalLayout grafico = new VerticalLayout();
        grafico.setWidth("600px");

        for (Map.Entry<String, Long> entry : pessoasPorPais.entrySet()) {
            String pais = entry.getKey();
            long qtd = entry.getValue();

            // rótulo + barra de progresso
            HorizontalLayout linha = new HorizontalLayout();
            linha.setWidthFull();
            linha.setDefaultVerticalComponentAlignment(Alignment.CENTER);

            Span label = new Span(pais + " (" + qtd + ")");
            label.getStyle().set("min-width", "120px");

            ProgressBar barra = new ProgressBar(0, max, qtd);
            barra.setWidth("100%");
            barra.getElement().getStyle().set("height", "20px");

            linha.add(label, barra);
            grafico.add(linha);
        }
        add(grafico);

        long total = pessoasPorPais.values().stream().mapToLong(Long::longValue).sum();
        if (total == 0) {
            add(new Span("Nenhum dado disponível."));
            return;
        }

        // Criar gradiente para a pizza
        StringBuilder gradient = new StringBuilder("conic-gradient(");
        double acumulado = 0;
        int corIndex = 0;
        String[] cores = { "#4CAF50", "#2196F3", "#FFC107", "#F44336", "#9C27B0" };

        for (Map.Entry<String, Long> entry : pessoasPorPais.entrySet()) {
            double inicio = (acumulado / total) * 100;
            acumulado += entry.getValue();
            double fim = (acumulado / total) * 100;

            String cor = cores[corIndex % cores.length];
            gradient.append(cor).append(" ").append(inicio).append("% ").append(fim).append("%, ");
            corIndex++;
        }

        // remover vírgula extra e fechar
        String cssGradient = gradient.substring(0, gradient.length() - 2) + ")";

        // Criar div da pizza
        Div pizza = new Div();
        pizza.getStyle().set("width", "300px");
        pizza.getStyle().set("height", "300px");
        pizza.getStyle().set("border-radius", "50%");
        pizza.getStyle().set("background", cssGradient);
        pizza.getStyle().set("margin", "20px auto");

        add(pizza);

        // Legenda
        VerticalLayout legenda = new VerticalLayout();
        legenda.setDefaultHorizontalComponentAlignment(Alignment.START);

        corIndex = 0;
        for (Map.Entry<String, Long> entry : pessoasPorPais.entrySet()) {
            String cor = cores[corIndex % cores.length];
            Div item = new Div();
            item.add(new Span(entry.getKey() + " (" + entry.getValue() + ")"));
            item.getStyle().set("display", "flex").set("align-items", "center");

            Div quadrado = new Div();
            quadrado.getStyle()
                    .set("width", "16px")
                    .set("height", "16px")
                    .set("background", cor)
                    .set("margin-right", "8px");
            item.getElement().insertChild(0, quadrado.getElement());
            legenda.add(item);

            corIndex++;
        }

        add(legenda);


        // Botão para voltar
        Button voltar = new Button("Voltar para Página Principal", 
            e -> getUI().ifPresent(ui -> ui.navigate("")));
        voltar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(voltar);
    }
}
