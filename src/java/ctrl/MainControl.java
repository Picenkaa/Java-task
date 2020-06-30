package ctrl;

import org.springframework.stereotype.Controller;
import ds.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping //Maps category url
public class MainControl {
// grazinti sarasa 
    @RequestMapping(value = "/get", method = RequestMethod.GET) //Maps category/getCat
    @ResponseStatus(value= HttpStatus.OK)
    @ResponseBody //Kad grąžintų atsakymą iš db į browserį
    public String get() {    //http://localhost:9999/WS_full/buildings/get
        registry a = new registry();
        return a.gautiSarasa().toString();
    }
  

 
    @RequestMapping(value ="/add_{ad}_{ow}_{s}_{m}_{pt}",method = RequestMethod.POST) 
    @ResponseStatus(value=HttpStatus.OK)
    @ResponseBody  
    //link'as perduodant parametrus atrodys taip: http://localhost:9999/WS_full/buildings/add_slenio_zigimantas_40_20000_sodas
    public ModelAndView add(@PathVariable String ad, @PathVariable String ow, @PathVariable int s, @PathVariable int m, @PathVariable String pt) { //ModelAndView: Represents a model and view returned by a handler, to be resolved by a DispatcherServlet.
        //t.y. sėkmės ir nesėkmės atveju įvyks redirect į kažkokį puslapį
        registry a = new registry();
        try {
            boolean success = a.pridetiKategorija(ad,ow,s,m,pt); //Kviečiame kategorijų pridėjimą
            if (success) {
                return new ModelAndView("redirect:/index.htm");
            } else {
                return new ModelAndView("redirect:/klaida.htm");
            }
        } catch (Exception e) {
           return new ModelAndView("redirect:/klaida.htm");
           
        }
    }

    @RequestMapping(value= "/delCat_{pavadinimas}", method = RequestMethod.DELETE)
    //link'as perduodant parametrus atrodys taip: http://localhost:8080/WS_full/category/delCat_testinis
    public ModelAndView deleteCategory(@PathVariable String pavadinimas) {
        registry afv = new registry();
        try {
            boolean a = afv.salintiKategorija(pavadinimas);
            if (a) {
                return new ModelAndView("redirect:/index.htm");
            } else {
                return new ModelAndView("redirect:/klaida.htm");
            }
        } catch (Exception e) {
            return new ModelAndView("redirect:/klaida.htm");
        }
    }
    //Kintamieji mappinge ir metodo argumentu pavadinimai turi sutapti
    @RequestMapping(value= "/updCat_{pavadinimasSenas}_{pavadinimasNaujas}",method = RequestMethod.PUT)
    //link'as perduodant parametrus atrodys taip: http://localhost:8080/WS_full/category/updCat_testinis_testinis2
    public ModelAndView updateCategory(@PathVariable String pavadinimasSenas, @PathVariable String pavadinimasNaujas) {
        registry afv = new registry();
        try {
            boolean a = afv.keistiKategPavad(pavadinimasSenas, pavadinimasNaujas);
            if (a) {
                return new ModelAndView("redirect:/index.htm");
            } else {
                return new ModelAndView("redirect:/klaida.htm");
            }
        } catch (Exception e) {
            return new ModelAndView("redirect:/klaida.htm");
        }
    }

}
