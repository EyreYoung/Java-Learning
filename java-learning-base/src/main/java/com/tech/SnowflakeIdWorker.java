package com.tech;

public class SnowflakeIdWorker {
    private final long twepoch = 1288834974657L; // 自定义的纪元时间
    private final long workerIdBits = 5L;       // 机器ID的位数
    private final long datacenterIdBits = 5L;   // 数据中心ID的位数
    private final long sequenceBits = 12L;      // 每毫秒产生的序列号的位数

    private final long workerIdShift = sequenceBits;   // 序列号向左移12位
    private final long datacenterIdShift = sequenceBits + workerIdBits;  // 数据中心ID向左移17位
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; // 时间戳向左移22位
    private final long sequenceMask = ~(-1L << sequenceBits);  // 生成序列号的掩码

    private long workerId;  // 工作机器 ID
    private long datacenterId;  // 数据中心 ID
    private long sequence = 0L; // 每毫秒产生的序列号
    private long lastTimestamp = -1L; // 上次生成 ID 的时间戳

    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > 31 || workerId < 0) {
            throw new IllegalArgumentException("workerId can't be greater than 31 or less than 0");
        }
        if (datacenterId > 31 || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than 31 or less than 0");
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowflakeIdWorker worker = new SnowflakeIdWorker(1, 2);
        System.out.println(worker.nextId());
    }
}

