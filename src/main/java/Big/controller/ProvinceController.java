package Big.controller;

import Big.model.Customer;
import Big.model.Province;
import Big.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import Big.service.IProvinceService;

import java.util.Optional;

@Controller
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView saveProvince(@ModelAttribute Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("provinces",province);
        modelAndView.addObject("message","Created new province");
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        if(province.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/province/edit");
            modelAndView.addObject("province",province.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@ModelAttribute Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("province",province);
        modelAndView.addObject("message","Province updated successfuly");
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        if(province.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/province/delete");
            modelAndView.addObject("province",province.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }
    @PostMapping("/delete-province")
    public ModelAndView deleteProvince(@ModelAttribute Province province){
        provinceService.remove(province.getId());
        return new ModelAndView("redirect:/provinces");
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable Long id, Pageable pageable){
        Optional<Province> province = provinceService.findById(id);
        if(!province.isPresent()){
            return new ModelAndView("/error.404");
        }
        Page<Customer> customers = customerService.findAllByProvince(province.get(), pageable);
        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province",province.get());
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }
}
