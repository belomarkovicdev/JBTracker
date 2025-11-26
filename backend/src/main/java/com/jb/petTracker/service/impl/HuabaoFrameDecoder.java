package com.jb.petTracker.service.impl;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.ArrayList;
import java.util.List;

public class HuabaoFrameDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {

        if (in.readableBytes() < 5) return;

        int start = -1;
        int end = -1;

        for (int i = in.readerIndex(); i < in.writerIndex(); i++) {
            byte b = in.getByte(i);

            if (b == 0x7E && start == -1) {
                start = i;
            } else if (b == 0x7E && start != -1) {
                end = i;
                break;
            }
        }

        if (start != -1 && end != -1 && end > start) {
            ByteBuf frame = in.retainedSlice(start + 1, end - start - 1);
            in.readerIndex(end + 1);

            byte[] raw = new byte[frame.readableBytes()];
            frame.readBytes(raw);

            byte[] unescaped = unescape(raw);

            out.add(unescaped);
        }
    }

    private byte[] unescape(byte[] data) {
        List<Byte> output = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0x7D) {
                i++;
                if (data[i] == 0x01) output.add((byte) 0x7D);
                else if (data[i] == 0x02) output.add((byte) 0x7E);
            } else {
                output.add(data[i]);
            }
        }

        byte[] arr = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) arr[i] = output.get(i);
        return arr;
    }
}