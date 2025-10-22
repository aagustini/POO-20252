package aagustini.poo;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Demo 4 - Cadastro de pessoas - Edição")
@Route("demo4")
public class Demo4View extends VerticalLayout {

    // Instância do cadastro de pessoas
    private final CadastroPessoas cadPessoas = CadastroPessoas.getInstance();

    // Campos do formulário
    private final TextField nome = new TextField("Nome");
    private final TextField email = new TextField("EMail");
    private final DatePicker dataNascimento = new DatePicker("Data de Nascimento");
    private final ComboBox<String> pais = new ComboBox<>("País");

    // Grid para exibir as pessoas
    private final Grid<Pessoa> grid = new Grid<>(Pessoa.class);
    
    private final Button salvar = new Button("Atualizar", VaadinIcon.CHECK.create());
    private final Button cancelar = new Button("Cancelar");

    Pessoa pessoaSelecionada;

    public Demo4View() {
        setSpacing(true);
        setPadding(true);

        add(new H2("Demo 4 - Cadastro de pessoas - Edição"));

        // Configuração do formulário e ComboBox
        pais.setItems("Brasil", "Portugal", "EUA", "Inglaterra");
        
        // Tornar o campo 'nome' não pode ser atualizado (readonly)
        nome.setReadOnly(true); 
        
        FormLayout formLayout = new FormLayout(nome, email, dataNascimento, pais);

        
        // Configuração dos botões de ação
        salvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout botoesLayout = new HorizontalLayout(salvar, cancelar);

        // Configuração da Grid
        grid.setItems(cadPessoas.getLista());
        grid.setColumns("nome", "email", "pais", "dataNascimento");
  
        // Ao selecionar elemento no grid...
        grid.asSingleSelect().addValueChangeListener(event -> {
            pessoaSelecionada = event.getValue();
            
            if (pessoaSelecionada != null) {
                // Se uma pessoa foi selecionada, preenche o formulário
                preencherFormulario(pessoaSelecionada);
                setFormularioEnabled(true);
            } else {
                // Se a seleção foi limpa, limpa o formulário
                limparFormulario();
                setFormularioEnabled(false);
            }
        });
        

        // Lógica do botão Salvar/Atualizar

        salvar.addClickListener(click -> {
            Pessoa p = new Pessoa( nome.getValue(),
                                    email.getValue(),
                                    pais.getValue(),
                                    dataNascimento.getValue());
            
            cadPessoas.update(pessoaSelecionada.getID(), p); 
            
            String mensagem = "Usuário " + p.getNome() + " atualizado com sucesso!";
            Notification.show(mensagem, 3000, Notification.Position.BOTTOM_STRETCH);
            
            grid.getDataProvider().refreshAll();
            limparFormulario();
            setFormularioEnabled(false); // Desabilita o form após salvar
        });

        // Configuração do diálogo de confirmação para o cancelamento
        Dialog dialogo = new Dialog();
        dialogo.setHeaderTitle("Confirmar cancelamento");
        dialogo.add(new Paragraph("Você tem certeza que deseja cancelar e limpar o formulário?"));
        Button confirmarCancelamento = new Button("Sim, cancelar", e -> {
            limparFormulario();
            setFormularioEnabled(false); // Desabilita o form ao cancelar
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
        backButton.addClickListener(e -> UI.getCurrent().navigate("hello"));

        add(backButton);

        // MUDANÇA 3: Desabilitar o formulário inicialmente
        setFormularioEnabled(false);
    }

    /**
     * Preenche o formulário a partir do grid
     */
    private void preencherFormulario(Pessoa pessoa) {
        nome.setValue(pessoa.getNome());
        email.setValue(pessoa.getEmail());
        dataNascimento.setValue(pessoa.getDataNascimento());
        pais.setValue(pessoa.getPais());
    }

    /**
     * Habilitar/desabilitar os campos do formulário
     */
    private void setFormularioEnabled(boolean enabled) {
        // 'nome' é readonly, então não mexemos no 'enabled' dele
        email.setEnabled(enabled);
        dataNascimento.setEnabled(enabled);
        pais.setEnabled(enabled);
        salvar.setEnabled(enabled);
        cancelar.setEnabled(enabled);
    }

    /**
     * Limpa seleção do grid
     */
    private void limparFormulario() {
        // Desseleciona qualquer item na grid (isso evita loops de eventos)
        grid.asSingleSelect().clear(); 
        
        nome.clear();
        dataNascimento.clear();
        pais.clear();
        email.clear();
        nome.focus(); // Coloca o foco no campo nome
    }
}