package aagustini.poo;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Demo 1 - Primeiros componentes")
@Route("/demo1")
public class Demo1View extends VerticalLayout {
    public Demo1View() {
 
    // H1 a H6 são "tags html" que definem títulos
    add(new H1("Demo 1 de componentes"));

    // podemos escrever textos com Text
    Text text1 = new Text("Texto inicial com Text");
 
    // para visualizar na páginas adicionamos à view
    add(text1);
    add(new Text("-- Segundo texto --"));

    add(new Hr());

    Span span1 = new Span("Adicionando uma linha de texto com Span");
    Span span2 = new Span("Textos no Span permitem manipular estilos...");
    span2.getElement().getStyle().set("font-size","30px")
                                .set("font-weight", "bold")
                                .set("font-weight", "italic");
    add(span1);
    add(span2);

    add(new Hr());
    // 
    // Caixas de texto e um pouco de diálogos
  
    TextField nomeTextField = new TextField("Nome");
    TextField emailTextField = new TextField("Email");
    TextArea commentTextArea = new TextArea(); 


    // veja o uso do evento no construtor...
    Button cancelar = new Button("Cancelar",
                                e -> Notification.show("Pressionado cancelar!"));
    Button salvar = new Button("Salvar", 
                                e -> Notification.show("Pressionado salvar!"));
    

    HorizontalLayout botoesLayout = new HorizontalLayout(cancelar, salvar);
    VerticalLayout layoutPrincipal = new VerticalLayout(nomeTextField, emailTextField, commentTextArea, botoesLayout);

    add(layoutPrincipal);
    add(botoesLayout);

    add(new Hr());
    // Retornando à página principal
    Button backButton = new Button("Voltar");
    backButton.addClickListener(e -> UI.getCurrent().navigate("hello") );

    add(backButton);
    
    }
}

