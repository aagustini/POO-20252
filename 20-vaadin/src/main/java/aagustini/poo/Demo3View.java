package aagustini.poo;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Demo 3 - Cadastro de pessoas - Inserção")
@Route("demo3")
public class Demo3View extends VerticalLayout {

    // Instância do cadastro de pessoas
    private final CadastroPessoas cadPessoas = CadastroPessoas.getInstance();

    // Campos do formulário
    private final TextField nome = new TextField("Nome");
    private final TextField email = new TextField("EMail");
    private final DatePicker dataNascimento = new DatePicker("Data de Nascimento");
    private final ComboBox<String> pais = new ComboBox<>("País");
    private final Checkbox aceitaTermos = new Checkbox("Aceito os termos de serviço");

    // Grid para exibir as pessoas
    private final Grid<Pessoa> grid = new Grid<>(Pessoa.class);

    public Demo3View() {
        setSpacing(true);
        setPadding(true);

        add(new H2("Demo 3 - Cadastro de pessoas - INSERÇÃO"));

        // Configuração do formulário e ComboBox
        pais.setItems("Brasil", "Portugal", "EUA", "Inglaterra");
        FormLayout formLayout = new FormLayout(nome, email, dataNascimento, pais, aceitaTermos);


        // Botões de ação
        Button salvar = new Button("Inserir", VaadinIcon.CHECK.create());
        salvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancelar = new Button("Cancelar");
        HorizontalLayout botoesLayout = new HorizontalLayout(salvar, cancelar);

        // Configuração da Grid
        grid.setItems(cadPessoas.getLista());
        grid.setColumns("nome", "email", "pais", "dataNascimento");
        
        // Lógica do botão Inserir
        salvar.addClickListener(click -> {
            if (aceitaTermos.getValue() == false) {
                 Notification.show("Você precisa aceitar os termos de serviço.", 3000, Notification.Position.TOP_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                 return;
            } else {
                Pessoa p = new Pessoa( nome.getValue(),
                                       email.getValue(),
                                       pais.getValue(),
                                       dataNascimento.getValue());
                cadPessoas.cadastrar(p);
                
                String mensagem = "Usuário " + p.getNome() + " salvo com sucesso!";
                Notification.show(mensagem, 3000, Notification.Position.BOTTOM_STRETCH);
                
                grid.getDataProvider().refreshAll();
                limparFormulario();
            }
        });

        // Configuração do diálogo de confirmação para o cancelamento
        Dialog dialogo = new Dialog();
        dialogo.setHeaderTitle("Confirmar cancelamento");
        dialogo.add(new Paragraph("Você tem certeza que deseja cancelar e limpar o formulário?"));
        Button confirmarCancelamento = new Button("Sim, cancelar", e -> {
            limparFormulario();
            dialogo.close();
        });
        confirmarCancelamento.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        Button fecharDialogo = new Button("Não", e -> dialogo.close());
        dialogo.getFooter().add(fecharDialogo, confirmarCancelamento);

        cancelar.addClickListener(click -> dialogo.open());
        salvar.addClickShortcut(Key.ENTER);

        add(formLayout, botoesLayout, new H2("Usuários Cadastrados"), grid);
    
    add(new Hr());
    // Retornando à página principal
    Button backButton = new Button("Voltar");
    backButton.addClickListener(e -> UI.getCurrent().navigate("hello") );

    add(backButton);
     
    }


    private void limparFormulario() {
        nome.clear();
        dataNascimento.clear();
        pais.clear();
        email.clear();
        nome.focus(); // Coloca o foco no campo nome
    }
}