package ma.emsi.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.entities.Medecin;
import ma.emsi.patientsmvc.entities.Nurse;
import ma.emsi.patientsmvc.repositories.MedecinRepository;
import ma.emsi.patientsmvc.repositories.NurseRepository;
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
public class NurseController {
    private NurseRepository nurseRepository;
    @GetMapping(path ="/user/index2")
    public String medecins(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5")int size,
                           @RequestParam(name = "keyword",defaultValue = "")String keyword
    ){
        Page<Nurse> pageNurses = nurseRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listNurses",pageNurses.getContent());
        model.addAttribute("pages",new int[pageNurses.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "nurses";
    }

    @GetMapping("/admin/delete2")
    public String delete2(Long id, String keyword, int page){
        nurseRepository.deleteById(id);
        return "redirect:/user/index2?page="+"&keyword="+keyword;
    }


    @GetMapping("/user/nurses")
    @ResponseBody
    public List<Nurse> listNurses(){
        return nurseRepository.findAll();
    }

    @GetMapping("/admin/formNurses")
    public String formNurses(Model model){
        model.addAttribute("nurse",new Nurse());
        return "formNurses";
    }

    @PostMapping(path="/admin/save2")
    public String save2(Model model, @Valid Nurse nurse, BindingResult bindingResult,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formNurses";
        nurseRepository.save(nurse);
        return "redirect:/user/index2?page="+page+"&keyword"+keyword;

    }

    @GetMapping("/admin/editNurse")
    public String editNurse(Model model, Long id, String keyword, int page){
        Nurse nurse=nurseRepository.findById(id).orElse(null);
        if(nurse==null) throw new RuntimeException("Nurse introuvable !");
        model.addAttribute("nurse",nurse);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editNurses";
    }

}
