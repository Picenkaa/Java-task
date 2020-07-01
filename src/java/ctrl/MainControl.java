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
            boolean success = a.prideti(ad,ow,s,m,pt); //Kviečiame kategorijų pridėjimą
            if (success) {
                return new ModelAndView("redirect:/index.htm");
            } else {
                return new ModelAndView("redirect:/klaida.htm");
            }
        } catch (Exception e) {
           return new ModelAndView("redirect:/klaida.htm");
           
        }
    }

    @RequestMapping(value= "/del_{addd}", method = RequestMethod.DELETE)
    //link'as perduodant parametrus atrodys taip:http://localhost:9999/WS_full/buildings/del_slenio // addressa 
    @ResponseStatus(value=HttpStatus.OK)
      @ResponseBody  
    public ModelAndView delete(@PathVariable String addd) {
        registry afv = new registry();
        try {
            boolean a = afv.salinti(addd);
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
    @RequestMapping(value= "/upd_{ad}_{nmv}",method = RequestMethod.PUT)
    //link'as perduodant parametrus atrodys taip:http://localhost:9999/WS_full/buildings/upd_slenio_20
    @ResponseStatus(value=HttpStatus.OK)
      @ResponseBody  
    public ModelAndView update(@PathVariable String ad, @PathVariable int nmv) {
        registry afv = new registry();
        // int nwmi=Integer.parseInt(nmv);  
        try {
            boolean a = afv.keisti(ad, nmv);
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
