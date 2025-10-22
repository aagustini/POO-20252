package aagustini.poo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("hello")
public class MainView extends VerticalLayout {
    public MainView() {
       Button sayHello = new Button("Say hello");
       sayHello.addClickListener(e -> {
             Notification.show("Hello world!");
       });
       add(sayHello);

       add(new Hr());

    RouterLink linkParaDemo1 = new RouterLink("Ir para o demo 1 - Primeiros componentes", Demo1View.class);
    add(linkParaDemo1);
    add( new RouterLink("Ir para o demo 2 - mais alguns componentes", Demo2View.class));
    add( new RouterLink("Ir para o demo 3 - cadastro de pessoas - inserção", Demo3View.class));
    add( new RouterLink("Ir para o demo 4 - cadastro de pessoas - edição", Demo4View.class));

   }

}
