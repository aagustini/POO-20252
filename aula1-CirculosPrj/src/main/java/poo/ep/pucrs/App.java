package poo.ep.pucrs;

public class App {
    public static void main(String[] args) {

        Circulo c1 = new Circulo(3, 4, 2);
         System.out.println("\nc1 ao criar: " + c1.toString());
         System.out.println("area de c1: " + c1.area());

        Circulo c2;
        c2 = new Circulo();
         System.out.println("c2: " + c2.toString());
         System.out.println("area de c2: " + c2.area());

         c1.mover(7, 8);
         System.out.println("\nc1 ao mover: " + c1.toString());

         c1.zoom(2);
         System.out.println("\nc1 dar zoom: " + c1.toString());

         // com array...
         Circulo[] lista;                // lista <- null
         // para transformar o array numa lista eu controlo os elementos
         // neste caso toda responsabiliade é do programador
         int nroElementos = 0;

                                         //   (0)   (1)   (2)
         lista = new Circulo[3];         // [ null, null, null ]

         lista[0] = new Circulo(1,2,3);
         nroElementos = nroElementos+1;
         lista[1] = c1;
         nroElementos = nroElementos+1;
         lista[2] = c2;
         nroElementos = nroElementos+1;

        System.out.println("\nMeu array de circulos");
         for(int i = 0; i<nroElementos; i++) {
             System.out.printf("%s \t area: %.2f\n", 
                             lista[i],
                             lista[i].area());
         }

    //     //System.out.println("\nMeu array de circulos, de forma mais legal...");        
    //     //for (Circulo umCirculo : lista) {
    //     //    System.out.printf("%s \t area: %.2f\n", 
    //     //                    umCirculo.toString(),
    //     //                    umCirculo.area());
    //    // }


    //    // mais adequado... usar a biblioteca da linguagem java
    //    // Collections framework --> classe que implementa uma lista

    //    ArrayList<Circulo> circulos;

    //    circulos = new ArrayList<>();

    //     circulos.add(c1);
    //     circulos.add(c2);
    //     circulos.add(new Circulo(5,6,7));
    //     circulos.add(new Circulo());

    //     circulos.remove(1);

    //     System.out.println("\nMeu lista de circulos");
    //     for(int i = 0; i<circulos.size(); i++) {
    //         System.out.printf("%s \t area: %.2f\n", 
    //                         circulos.get(i),
    //                         circulos.get(i).area());
    //     }

    //     System.out.println("\nMinha de circulos, de forma mais legal...");        
    //     for (Circulo umCirculo : circulos) {
    //         System.out.printf("%s \t area: %.2f\n", 
    //                         umCirculo.toString(),
    //                         umCirculo.area());
    //     }    


    }
}
