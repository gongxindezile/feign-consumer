feign 项目只写 实体类 和 interface接口（指向另个一微服务的controller接口） ，当2个微服务模块之间 涉及相互调用的时候， 可以注解注入 这个feign接口， 来调用就可以了

package cn.com.crc.ems.feign;

/**
 * 预算rest服务
 * @author LIUSHUAI229
 *
 */
@FeignClient(name= "BUDGET-DOMAIN-XIATIAO")
//@FeignClient("${budget.domain}")
public interface RBudgetDomainClient {

	@RequestMapping(value = "/budgetAmountDetailController/merge", method = {RequestMethod.POST})
	Boolean merge(@RequestBody BudgetAmountImportDto budgetAmountImportDto);

	@RequestMapping(value = "/budgetAmountDetailController/update", method = {RequestMethod.POST})
	Object update(@RequestBody List<BudgetAmountEntryAdjustDto> entryAdjustDtos);
	/**
	 * 扣减库存
	 * @param productId
	 * @param count
	 * @return
	 */
	@GetMapping(value = "/seataDemoStorage/decrease")
	String storageDecrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

	/**
	 * 扣减账户余额
	 * @param userId 用户id
	 * @param money 金额
	 * @return
	 */
	@RequestMapping("/seataDemoAccount/decrease")
	String accountDecrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

	/**
	 *@描述 预算申报
	 *@参数 BudgetBaseDto
	 *@返回值 R
	 *@创建人 LIUSHUAI229
	 *@创建时间 2020/1/19
	 *@修改人和其它信息
	 */

	@RequestMapping(value = "/budgetPlanController/add", method = {RequestMethod.POST})
	R add(@RequestBody BudgetBaseDto budgetBaseDto);
}
