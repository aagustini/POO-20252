package poo.ep.pucrs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        setSpacing(true);
        setPadding(true);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // Imagem no topo (pode ser um logo ou banner)
       // Image logo = new Image("https://www.clipartmax.com/png/small/189-1893157_los-simpson-logo-png.png", "Logo");
        Image logo = new Image("images/soupucrs.jpeg", "Sou PUCRS");
        //logo.setMaxWidth("1000px");

        // Título
        H1 titulo = new H1("Sistema de Gestão de Pessoas");

        // Botão para Cadastro de Pessoas
        Button pessoasBtn = new Button("Cadastro de Pessoas", VaadinIcon.USER.create(), 
            e -> getUI().ifPresent(ui -> ui.navigate("pessoas")));
        pessoasBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);

        // Botão para Gestão
        Button gestaoBtn = new Button("Gestão", VaadinIcon.CHART.create(), 
            e -> getUI().ifPresent(ui -> ui.navigate("gestao")));
        gestaoBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_LARGE);

        // Layout dos botões
        HorizontalLayout botoes = new HorizontalLayout(pessoasBtn, gestaoBtn);
        botoes.setSpacing(true);
        botoes.setJustifyContentMode(JustifyContentMode.CENTER);

        // Monta a tela
        add(logo, titulo, botoes);
        setAlignItems(Alignment.CENTER);
    }
}
