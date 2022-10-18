package spring.mvc.session09.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/lotto")
@Controller
public class LottoController {

	//重導到主畫面
	private String redirect_indexView = "redirect:/mvc/lotto/";
	
	//存放所有 lotto 紀錄的集合
	private List<Set<Integer>> lottos = new CopyOnWriteArrayList<Set<Integer>>();
	
	// Lotto 主畫面
	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("lottos",lottos);
		return "session09/lotto";
	}
	
	// 取得最新電腦選號 
	@GetMapping(value = {"/get" ,"/add"})
	public String get(Model model) {
		// 取得最新 lotto 資料
		Set<Integer> lotto = getRandomLotto();
		// 將 lotto 放入 lottos 歷史資料
		lottos.add(lotto);
		// 要回傳給 jsp 的資料
		model.addAttribute("lotto",lotto); // 最新資料
		model.addAttribute("lottos",lottos); // 歷史資料
		//jsp 所在位置
		return "session09/lotto";
	}
	
	// 修改指定index 的樂透紀錄   注意 : {index} 不可加空白
	@GetMapping(value = "/update/{index}")
	public String update(@PathVariable("index")int index) {
		// 重新取得樂透號碼
		Set<Integer> newLotto = getRandomLotto();
		// 將 Lotto 放在指定index的位置(更新)
		lottos.set(index, newLotto);
		// 修改完 重新將資料丟回給jsp
		//model.addAttribute("lottos",lottos);
		return redirect_indexView;
	}
	
	// 變更指定[row][col]號碼
	@GetMapping("/change/{row}/{col}")
	public String change(@PathVariable("row")int row, @PathVariable("col")int col) {
		Set<Integer> curr = lottos.get(row);
		List<Integer> list = new ArrayList<Integer>(curr); // 將 set 轉 list
		
		while (true) {
			int n = new Random().nextInt(39)+1;
			if(!curr.contains(n)) { // 如果 curr 內 (!不包含) n值 那就 set 進去
				list.set(col, n);
				break;
			}
			
		}
		curr = new LinkedHashSet<Integer>(list); // 將 list 轉 set
		lottos.set(row, curr);
		return redirect_indexView;
	}
	
	// 刪除指定index 的樂透紀錄 
	@GetMapping(value = "/delete/{index}")
	public String delete(@PathVariable("index")int index) {
		// 根據 index 位置來刪除該筆紀錄
		lottos.remove(index);
		// 刪除完 重新將資料丟回給jsp
		//model.addAttribute("lottos",lottos);
		return redirect_indexView;
	}
	
	// 統計每一個號碼出現的次數
	@GetMapping(value = "/stat")
	public String stat(Model model) {
		// 1. 將所有的資料 先利用 flatMap 拆散 再透過 collect 蒐集
		List<Integer> nums = lottos.stream()
				.flatMap(lotto -> lotto.stream()) // Stream<Integer>
						.collect(toList()); // list<Integer>
		// 2. 透過 groupby 將資料分組
		Map<Integer, Long> stat = nums.stream()
										.parallel() // 平行運算
										.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
							
		// 3. 加上排序 (反向排序)
		Map<Integer , Long> result = new LinkedHashMap<Integer, Long>(); // 存放排序後的結果
		stat.entrySet().stream()
			.sorted(Map.Entry.<Integer,Long>comparingByValue().reversed()) // 根據 value 反排序
			.forEachOrdered(e -> result.put(e.getKey() , e.getValue())); // 將結果依序放到 result 中
		
		stat = result; // 重新指向
		model.addAttribute("stat",stat);
		model.addAttribute("lottos",lottos);
		return "session09/lotto";
	}
	
	
	// 隨機產生樂透電腦選號
	private Set<Integer> getRandomLotto(){
		// 1~39 取五個不重複的數字
		Random r=  new Random();
		Set<Integer> lotto = new LinkedHashSet<Integer>();
		while (lotto.size() < 5) {
			lotto.add(r.nextInt(39)+1);
		}
		return lotto;
	}
	
}