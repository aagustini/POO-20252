package poo.ep.pucrs;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("pessoas")
public class MainView extends VerticalLayout {

    public MainView() {

        CadastroPessoas cadPessoas = CadastroPessoas.getInstance();


        setSpacing(true);
        setPadding(true);

        add(new H2("Cadastro de pessoas"));

        FormLayout formLayout = new FormLayout();
        TextField nome = new TextField("Nome");
        TextField email = new TextField("EMail");
        DatePicker dataNascimento = new DatePicker("Data de Nascimento");
        ComboBox<String> pais = new ComboBox<>("País");
        pais.setItems("Brasil", "Portugal", "EUA", "Inglaterra");
        Checkbox aceitaTermos = new Checkbox("Aceito os termos de serviço");
        formLayout.add(nome, email, dataNascimento, pais, aceitaTermos);
        
        Grid<Pessoa> grid = new Grid<>(Pessoa.class);


        Button salvar = new Button("Salvar", VaadinIcon.CHECK.create());
        salvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelar = new Button("Cancelar");

     
        HorizontalLayout botoesLayout = new HorizontalLayout(salvar, cancelar);


        salvar.addClickListener(click -> {
            if (nome.isEmpty() || dataNascimento.isEmpty() || pais.isEmpty()) {
                Notification notification = Notification.show("Por favor, preencha todos os campos obrigatórios!", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                cadPessoas.cadastrar(nome.getValue(),
                                     email.getValue(),
                                     pais.getValue(),
                                     dataNascimento.getValue());
                                
                String mensagem = "Usuário " + nome.getValue() + " salvo! País: " + pais.getValue()+ " DN: "+dataNascimento.getValue();
                grid.getDataProvider().refreshAll();
                Notification.show(mensagem, 3000, Notification.Position.BOTTOM_STRETCH);
            }
        });


        Dialog dialogo = new Dialog();
        dialogo.setHeaderTitle("Confirmar cancelamento");
        dialogo.add(new Paragraph("Você tem certeza que deseja cancelar e limpar o formulário?"));
        Button confirmarCancelamento = new Button("Sim, cancelar", e -> {
            nome.clear();
            email.clear();
            dataNascimento.clear();
            pais.clear();
            aceitaTermos.setValue(false);
            dialogo.close();
        });
        confirmarCancelamento.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        Button fecharDialogo = new Button("Não", e -> dialogo.close());
        dialogo.getFooter().add(fecharDialogo, confirmarCancelamento);

        cancelar.addClickListener(click -> dialogo.open());

        salvar.addClickShortcut(Key.ENTER);

        grid.setItems( cadPessoas.getLista() );
        grid.setColumns("nome", "email", "pais", "dataNascimento"); // Define as colunas a serem exibidas


        add(formLayout, botoesLayout, new H2("Usuários Cadastrados"), grid);
    }
}