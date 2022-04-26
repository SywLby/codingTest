package ServiceImpl;

import IRequirementBusiness.IRequirementBusiness;
import Service.IRequirementService;

import javax.annotation.Resource;

public class RequirementService implements IRequirementService {

    @Resource
    public IRequirementBusiness requirementBusiness;

    @Override
    public String requirementOne(String inputString) {
        String outPutResult = requirementBusiness.requirementOne(inputString);
        return outPutResult;
    }

    @Override
    public String requirementTwo(String inputString) {
        String outPutResult = requirementBusiness.requirementTwo(inputString);
        return outPutResult;
    }
}
