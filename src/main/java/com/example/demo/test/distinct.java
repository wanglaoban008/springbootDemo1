package com.example.demo.test;

import com.example.demo.aa.JavaLamda;
import com.example.demo.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @program:
 * @description:
 * @author: malili
 * @create: 2018-05-15 15:46
 **/
public class distinct {

    public static void main(String[] args) {
        Stream.of(new User("张三", "red", "")
                , new User("李四", "red", ""),
                new User("张三", "red", ""),
                new User("red", "李四", ""))
                .filter(distinctByKey(User::getDistinctKey)).forEach(System.out::println);
    }

    public static <T> Predicate<T> distinctByKey(JavaLamda.Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }

    //这个效率是logn2，放弃
//	public static void main(String[] args) {
//		List<ProInvestEntity> investEntityList = new ArrayList<>();
//		List<ProInvestEntity> investEntityList2 = new ArrayList<>();
//		List<ProInvestEntity> investEntityList5 = new ArrayList<>();
//		Set<ProInvestEntity> investEntitySet =new HashSet<>();
//		ProInvestEntity entity1 = new ProInvestEntity();
//		ProInvestEntity entity2 = new ProInvestEntity();
//		ProInvestEntity entity3 = new ProInvestEntity();
//		ProInvestEntity entity4 = new ProInvestEntity();
//		ProInvestEntity entity5 = new ProInvestEntity();
//		ProInvestEntity entity6 = new ProInvestEntity();
//		entity1.setCoinType("eos");
//		entity1.setCompanyAddress("地址1");
//		entity2.setCoinType("usdt");
//		entity2.setCompanyAddress("地址1");
//		entity3.setCoinType("eos");
//		entity3.setCompanyAddress("地址1");
//		//entity4.setCoinType("usdt");
//		//entity4.setCompanyAddress("地址2");
//		//entity5.setCoinType("eos");
//		//entity5.setCompanyAddress("地址1");
//		//entity6.setCoinType("eos");
//		//entity6.setCompanyAddress("地址2");
//		investEntityList.add(entity1);
//		investEntityList.add(entity2);
//		investEntityList.add(entity3);
//		//investEntityList.add(entity4);
//		//investEntityList.add(entity5);
//		//investEntityList.add(entity6);
//		//investEntityList.addAll(investEntityList);
//		//investEntityList2.addAll(investEntityList3);
//		System.err.println("investEntityList2.size():"+investEntityList2.size());
//		for (ProInvestEntity investEntity : investEntityList) {
//			if (!Objects.isNull(investEntity)){
//				if (investEntitySet != null && investEntitySet.size() > 0){
//					Set<ProInvestEntity> investSet =investEntitySet
//							.stream()
//							.filter(
//									proInvestEntity -> proInvestEntity.getCompanyAddress().equals(investEntity.getCompanyAddress())
//											&& proInvestEntity.getCoinType().equals(investEntity.getCoinType())).collect(Collectors.toSet());
//					if (CollectionUtils.isEmpty(investSet)){
//						investEntitySet.add(investEntity);
//					}
//					//System.err.println("aa:"+aa);
//				}else {
//					investEntitySet.add(investEntity);
//				}
//			}
//		}
//
//		System.err.println("set:"+investEntitySet.size());
//		investEntitySet.stream().forEach(proInvestEntity -> System.err.println("打印拼接："+proInvestEntity.getCompanyAddress()+proInvestEntity.getCoinType()));
//		investEntityList5 = investEntityList.stream().filter(distinctByKey(ProInvestEntity::getDistinctKey)).collect(Collectors.toList());
//		System.err.println("in5.size"+investEntityList5.size());
//		investEntityList5.stream().forEach(proInvestEntity -> System.err.println("打印拼接in5："+proInvestEntity.getCompanyAddress()+proInvestEntity.getCoinType()));
//	}
}
