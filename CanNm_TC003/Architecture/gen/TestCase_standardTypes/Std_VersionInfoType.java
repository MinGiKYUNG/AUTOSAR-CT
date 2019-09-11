package Architecture.gen_java.TestCase_standardTypes;

import java.util.*;

public class Std_VersionInfoType {
    protected int vendorId;  
   protected int moduleId;
   protected int instanceId;
   protected int sw_major_version;
   protected int sw_minor_version;
   protected int sw_patch_version;
		public Std_VersionInfoType() {}
		public Std_VersionInfoType(int vendorId, int instanceId) {
			this.vendorId = vendorId;
			this.instanceId = instanceId;}
		/**
		 * @return the vendorId */
		public int getVendorId() {
			return vendorId;}
		/**
		 * @param vendorId the vendorId to set */
		public void setVendorId(int vendorId) {
			this.vendorId = vendorId;}
		/**
		* @return the moduleId*/
		public int getModuleId() {
			return moduleId;}
		/**
		 * @param moduleId the moduleId to set */
		public void setModuleId(int moduleId) {
			this.moduleId = moduleId;}
		/**
		 * @return the instanceId */
		public int getInstanceId() {
			return instanceId;}
		/**
		 * @param instanceId the instanceId to set */
		public void setInstanceId(int instanceId) {
			this.instanceId = instanceId;}
		/**
		 * @return the sw_major_version */
		public int getSw_major_version() {
			return sw_major_version;}
		/**
		 * @param sw_major_version the sw_major_version to set */
		public void setSw_major_version(int sw_major_version) {
			this.sw_major_version = sw_major_version;}
		/**
		 * @return the sw_minor_version */
		public int getSw_minor_version() {
			return sw_minor_version;}
		/**
	 * @param sw_minor_version the sw_minor_version to set */
		public void setSw_minor_version(int sw_minor_version) {
			this.sw_minor_version = sw_minor_version;}
		/**
		 * @return the sw_patch_version */
		public int getSw_patch_version() {
			return sw_patch_version;}
		/**
		 * @param sw_patch_version the sw_patch_version to set*/
		public void setSw_patch_version(int sw_patch_version) {
			this.sw_patch_version = sw_patch_version;}
	}


