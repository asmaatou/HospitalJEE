package ma.emsi.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.entities.Medecin;
import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.MedecinRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class MedecinController {

    private MedecinRepository medecinRepository;
    @GetMapping(path ="/user/index1")
    public String medecins(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5")int size,
                           @RequestParam(name = "keyword",defaultValue = "")String keyword
    ){
        Page<Medecin> pageMedecins = medecinRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listMedecins",pageMedecins.getContent());
        model.addAttribute("pages",new int[pageMedecins.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "medecins";
    }

    @GetMapping("/admin/delete1")
    public String delete1(Long id, String keyword, int page){
        medecinRepository.deleteById(id);
        return "redirect:/user/index1?page="+"&keyword="+keyword;
    }


    @GetMapping("/user/medecins")
    @ResponseBody
    public List<Medecin> listMedecins(){
        return medecinRepository.findAll();
    }

    @GetMapping("/admin/formMedecins")
    public String formMedecins(Model model){
        model.addAttribute("medecin",new Medecin());
        return "formMedecins";
    }

    @PostMapping(path="/admin/save1")
    public String save1(Model model, @Valid Medecin medecin, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formMedecins";
        medecinRepository.save(medecin);
        return "redirect:/user/index1?page="+page+"&keyword"+keyword;

    }

    @GetMapping("/admin/editMedecin")
    public String editMedecin(Model model, Long id, String keyword, int page){
        Medecin medecin=medecinRepository.findById(id).orElse(null);
        if(medecin==null) throw new RuntimeException("Medecin introuvable !");
        model.addAttribute("medecin",medecin);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editMedecins";
    }



}
