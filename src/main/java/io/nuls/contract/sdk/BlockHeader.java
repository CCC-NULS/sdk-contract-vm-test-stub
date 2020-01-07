package io.nuls.contract.sdk;

import io.nuls.v2.model.dto.BlockHeaderDto;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockHeader {

    private String hash;
    private long time;
    private long height;
    private long txCount;
    private Address packingAddress;
    private String stateRoot;

    public  BlockHeader(){
    }

    public  BlockHeader(BlockHeaderDto dto){
        this.packingAddress=new Address(dto.getPackingAddress());
        this.height=dto.getHeight();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(dto.getTime(), pos);
        this.time=strtodate.getTime();
        this.stateRoot=dto.getStateRoot();
        this.txCount=dto.getTxHashList().size();
        this.hash=dto.getHash();
    }

    public String getHash() {
        return hash;
    }

    public long getTime() {
        return time;
    }

    public long getHeight() {
        return height;
    }

    public long getTxCount() {
        return txCount;
    }

    public Address getPackingAddress() {
        return packingAddress;
    }

    public String getStateRoot() {
        return stateRoot;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockHeader that = (BlockHeader) o;

        if (time != that.time) return false;
        if (height != that.height) return false;
        if (txCount != that.txCount) return false;
        if (hash != null ? !hash.equals(that.hash) : that.hash != null) return false;
        if (packingAddress != null ? !packingAddress.equals(that.packingAddress) : that.packingAddress != null)
            return false;
        return stateRoot != null ? stateRoot.equals(that.stateRoot) : that.stateRoot == null;
    }

    @Override
    public int hashCode() {
        int result = hash != null ? hash.hashCode() : 0;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (int) (height ^ (height >>> 32));
        result = 31 * result + (int) (txCount ^ (txCount >>> 32));
        result = 31 * result + (packingAddress != null ? packingAddress.hashCode() : 0);
        result = 31 * result + (stateRoot != null ? stateRoot.hashCode() : 0);
        return result;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public void setTxCount(long txCount) {
        this.txCount = txCount;
    }

    public void setPackingAddress(Address packingAddress) {
        this.packingAddress = packingAddress;
    }

    public void setStateRoot(String stateRoot) {
        this.stateRoot = stateRoot;
    }

    @Override
    public String toString() {
        return "BlockHeader{" +
                "hash='" + hash + '\'' +
                ", time=" + time +
                ", height=" + height +
                ", txCount=" + txCount +
                ", packingAddress=" + packingAddress +
                ", stateRoot='" + stateRoot + '\'' +
                '}';
    }
}
