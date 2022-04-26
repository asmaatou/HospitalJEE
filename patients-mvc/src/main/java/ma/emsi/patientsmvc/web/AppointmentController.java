package ma.emsi.patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientsmvc.entities.Appointment;
import ma.emsi.patientsmvc.entities.Medecin;
import ma.emsi.patientsmvc.repositories.AppointmentRepository;
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
public class AppointmentController {
    private AppointmentRepository appointmentRepository;

    @GetMapping(path ="/user/index3")
    public String appointments(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5")int size,
                           @RequestParam(name = "keyword",defaultValue = "")String keyword
    ){
        Page<Appointment> pageAppointments = appointmentRepository.findByPatientnameContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listAppointments",pageAppointments.getContent());
        model.addAttribute("pages",new int[pageAppointments.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "appointments";
    }

    @GetMapping("/admin/delete3")
    public String delete3(Long id, String keyword, int page){
        appointmentRepository.deleteById(id);
        return "redirect:/user/index3?page="+"&keyword="+keyword;
    }


    @GetMapping("/user/appointments")
    @ResponseBody
    public List<Appointment> listAppointments(){
        return appointmentRepository.findAll();
    }

    @GetMapping("/admin/formAppointments")
    public String formAppointments(Model model){
        model.addAttribute("appointment",new Appointment());
        return "formAppointments";
    }

    @PostMapping(path="/admin/save3")
    public String save3(Model model, @Valid Appointment appointment, BindingResult bindingResult,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formAppointments";
        appointmentRepository.save(appointment);
        return "redirect:/user/index3?page="+page+"&keyword"+keyword;

    }

    @GetMapping("/admin/editAppointment")
    public String editAppointment(Model model, Long id, String keyword, int page){
        Appointment appointment=appointmentRepository.findById(id).orElse(null);
        if(appointment==null) throw new RuntimeException("Appointment introuvable !");
        model.addAttribute("appointment",appointment);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editAppointments";
    }
}
