package DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @GetMapping("/ajouterContact")
    public String greeting(Model model) {
        model.addAttribute("contact", new Contact());
        return "ajouterContact";
    }
    
    //public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

    
	@Autowired
	ContactRepository contactRepository;
	
	@ModelAttribute("contacts")
	public Iterable<Contact> getListContacts() {
		return contactRepository.findAll();
	}
	
	@GetMapping("/listeContacts")
	public String listeContacts(Model model) {
		//model.addAttribute(contactRepository.findAll());
		return "listeContacts";
	}
	
	@PostMapping("/addContact")
	public String addContact(@ModelAttribute Contact contact) {
		contactRepository.save(contact);
		return "redirect:listeContacts";
	}
	
	@GetMapping("/modifyContact/{id}")
	public String editContact(Model model, @PathVariable("id") long id) {
		Optional<Contact> c = contactRepository.findById(id);
		model.addAttribute("contact", c);
        return "ajouterContact";
	}
	
	@GetMapping("/deleteContact/{id}")
	public String deleteContact(Model model, @PathVariable("id") long id) {
		contactRepository.deleteById(id);
		return "redirect:/listeContacts";
	}

}