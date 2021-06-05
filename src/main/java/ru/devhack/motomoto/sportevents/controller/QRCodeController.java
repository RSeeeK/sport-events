//package ru.devhack.motomoto.sportevents.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//import java.util.concurrent.*;
//
//@Slf4j
//@RestController
//@RequestMapping(ApiMeta.apiv1 + "/qr")
//public class QRCodeController {
//    private final static Long LONG_POLLING_TIMEOUT = 60000L;
//
//    private ExecutorService executorService;
//
//    private final ConcurrentHashMap<UUID, Future<UUID>> map = new ConcurrentHashMap<>();
//
//    public QRCodeController() {
//        executorService = Executors.newFixedThreadPool(5);
//    }
//
//    @GetMapping("/generate")
//    public UUID generate() {
//        UUID uuid = UUID.randomUUID();
//        map.put(uuid, executorService.submit(() -> {
//            try {
//                Thread.sleep(LONG_POLLING_TIMEOUT);
//                map.remove(uuid);
//            } catch (InterruptedException e) {
//                //register user for event
//                map.remove(uuid);
//            }
//        }, uuid));
//        return uuid;
//    }
//
//    @GetMapping("/wait/{qrcode}")
//    public String wait(@PathVariable UUID qrcode) {
//        try {
//            return map.get(qrcode).get().toString();
//        } catch (InterruptedException | ExecutionException | CancellationException e) {
//            return "OK";
//        }
//    }
//
//    @GetMapping("/over/{qrcode}")
//    public String over(@PathVariable UUID qrcode) {
//        if (map.containsKey(qrcode) && !map.get(qrcode).isDone() && !map.get(qrcode).isCancelled()) {
//            map.get(qrcode).cancel(true);
//            return "OK";
//        }
//        return "Error";
//    }
//}
