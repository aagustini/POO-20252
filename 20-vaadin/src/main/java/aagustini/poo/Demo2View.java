package aagustini.poo;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@PageTitle("Demo 2 - Mais alguns componentes")
@Route("demo2")
// Note que neste exemplo estamos utilizando um HorizontalLayout
public class Demo2View extends HorizontalLayout {

    TextArea logArea = new TextArea("Log de Eventos");

    public Demo2View() {
        
        // 1. Configuração do Layout Principal (this) 
        
        // Faz o layout ocupar toda a tela
        setSizeFull(); 
        // Remove o padding padrão para que as colunas ocupem 100%
        setPadding(false);
        setSpacing(false); // Remove o espaço entre as colunas E/D

        // 2. Criação do Layout da Esquerda (Formulário) 
        
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setWidth("60%"); // O formulário ocupa 60%
        formLayout.setHeightFull();       // Ocupa toda a altura
        
        // Adiciona um padding interno para os componentes não colarem nas bordas
        formLayout.getStyle().set("padding", "1em");
     
        // 3. Configuração do Layout da Direita (Log) 
        
        logArea.setWidth("40%");        // O log ocupa 40%
        logArea.setHeightFull();              // Ocupa toda a altura
        logArea.setReadOnly(true);   // area de log não é editavel

        
        //  4. Adicionar Componentes ao Layout da Esquerda (formLayout) 

        // - ListBox
        ListBox<String> frameworkListBox = new ListBox<>();
        frameworkListBox.setItems(List.of("Vaadin", "Spring Boot", "React", "Angular", "Vue"));
        frameworkListBox.addValueChangeListener(e ->
                this.log("\nFramework: " + frameworkListBox.getValue()));

        // - ComboBox
        ComboBox<String> linguagemComboBox = new ComboBox<>("Selecione uma linguagem de programação");
        linguagemComboBox.setItems(List.of("Java", "Python", "JavaScript", "C#", "Go"));
        linguagemComboBox.setPlaceholder("Escolha uma opção...");
        linguagemComboBox.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                this.log("\nComboBox selecionado: " + event.getValue());
            }
        });

        // - Checkbox
        Checkbox termosCheckbox = new Checkbox("Aceito os termos e condições de uso.");
        termosCheckbox.addValueChangeListener(event -> {
            String status = event.getValue() ? "marcado" : "desmarcado";
            this.log("\nCheckbox " + status);
        });

        // - DatePicker
        DatePicker dataNascimentoPicker = new DatePicker("Data de Nascimento");
        dataNascimentoPicker.setLocale(Locale.of("pt", "BR"));
        dataNascimentoPicker.setPlaceholder("DD/MM/AAAA");
        dataNascimentoPicker.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                String dataFormatada = event.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                this.log("\nData selecionada: " + dataFormatada);
            }
        });

        // - Botão para mostrat todos valores
        Button verificarValoresButton = new Button("\nTodos valores....", event -> {
            String linguagemSelecionada = linguagemComboBox.getValue();
            boolean termosAceitos = termosCheckbox.getValue();
            LocalDate dataNascimento = dataNascimentoPicker.getValue();
            String frameworkSelecionado = frameworkListBox.getValue();

            StringBuilder resultado = new StringBuilder();
            resultado.append("\nLinguagem: ")
                    .append(Optional.ofNullable(linguagemSelecionada).orElse("Nenhuma"))
                    .append("\n");
            resultado.append("Termos aceitos: ")
                    .append(termosAceitos ? "Sim" : "Não")
                    .append("\n");
            resultado.append("Data de Nascimento: ")
                    .append(Optional.ofNullable(dataNascimento)
                            .map(data -> data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                            .orElse("Nenhuma"))
                    .append("\n");
            resultado.append("Framework: ")
                    .append(Optional.ofNullable(frameworkSelecionado).orElse("Nenhum"))
                    .append("\n");

            this.log("\n\nDados do formulário: " + resultado.toString());
        });

        // Botão de voltar
        Button backButton = new Button("Voltar");
        backButton.addClickListener(e -> UI.getCurrent().navigate("hello"));

        //
        // Adicionando os componentes ao formLayout (esquerda)
        //
        
        formLayout.add(new H2("Demo 2 - Componentes Vaadin"));
        
        // ListBox não tem um label nativo, então adicionamos um texto antes
        formLayout.add(new H4("Selecione um framework"), frameworkListBox);
        
        // Agrupando o ComboBox e o Checkbox lado a lado para organizar
        HorizontalLayout comboCheckLayout = new HorizontalLayout(linguagemComboBox, termosCheckbox);
        comboCheckLayout.setAlignItems(Alignment.BASELINE); // Alinha na base
        
        formLayout.add(comboCheckLayout, dataNascimentoPicker, verificarValoresButton);
        
        formLayout.add(new Hr());
        formLayout.add(backButton);
        
        //  5. Adicionar os layouts E/D ao Layout Principal (this) 
        add(formLayout, logArea);
    }

    private void log(String text) {
        String valorAtual = logArea.getValue(); 
        logArea.setValue(valorAtual + text);
    }
    
}