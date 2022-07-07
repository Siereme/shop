package app.utils.httpHandler;

import app.model.dto.filter.Filter;
import app.model.dto.filter.FilterOption;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class HeaderFilterArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(app.utils.httpHandler.Filter.class) != null;
    }

    @Override
    public List<Filter> resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String param = request.getParameter("filter");
        String[] paramParts = param.split("&");
        List<Filter> filters = new ArrayList<>();

        for (String paramItem : paramParts) {
            String filterName = paramItem.split("=")[0];
            String[] filterParts = paramItem.split("=")[1].split(";");
            List<FilterOption> filterOptions = new ArrayList<>();
            for (String filterItem : filterParts) {
                String optionName = filterItem.split(":")[0];
                List<String> optionValues = List.of(filterItem.split(":")[1].split(","));
                FilterOption filterOption = new FilterOption(optionName, optionValues);
                filterOptions.add(filterOption);
            }
            Filter filter = new Filter(filterName, filterOptions);
            filters.add(filter);
        }
        return filters;
    }
}
