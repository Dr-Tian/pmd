package net.sourceforge.pmd.eclipse.ui.preferences.br;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RulePriority;
import net.sourceforge.pmd.eclipse.ui.nls.StringKeys;
import net.sourceforge.pmd.eclipse.util.ResourceManager;
import net.sourceforge.pmd.util.CollectionUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
/**
 * 
 * @author Brian Remedios
 */
public class IconColumnDescriptor extends AbstractRuleColumnDescriptor {

	private Map<Object, Image> iconsByValue;
	
	private static final Map<Object, String> iconNamesByValue = CollectionUtil.mapFrom(
			new Object[] { RulePriority.LOW, RulePriority.MEDIUM_LOW, RulePriority.MEDIUM, RulePriority.MEDIUM_HIGH, RulePriority.HIGH }, 
			new String[] {"btn_prio5.gif", "btn_prio4.gif", "btn_prio3.gif","btn_prio2.gif", "btn_prio1.gif"}
			);
	
	public static final RuleColumnDescriptor priority = new IconColumnDescriptor(StringKeys.MSGKEY_PREF_RULESET_COLUMN_PRIORITY, SWT.RIGHT, 53, RuleFieldAccessor.priority, true, iconNamesByValue, "/icons/");
	
	public IconColumnDescriptor(String labelKey, int theAlignment, int theWidth, RuleFieldAccessor theAccessor, boolean resizableFlag, Map<Object, String> imageNamesByValue, String optionalPath) {
		super(labelKey, theAlignment, theWidth, theAccessor, resizableFlag);

		iconsByValue = iconsFor(imageNamesByValue, optionalPath);
	}
	
	private static Map<Object, Image> iconsFor(Map<Object, String> imageNamesByValue, String optionalPath) {
		
		Map<Object, Image> imagesByValue = new HashMap<Object, Image>(imageNamesByValue.size());
		for (Map.Entry<Object, String> entry : imageNamesByValue.entrySet()) {
			String fullPath = optionalPath == null ? entry.getValue() : optionalPath + entry.getValue();
			imagesByValue.put(entry.getKey(), ResourceManager.imageFor(fullPath));
			}
		return imagesByValue;
	}
	
	public Image imageFor(Rule rule) {
		Object value = valueFor(rule);
		return iconsByValue.get(value);
	}

	public TreeColumn newTreeColumnFor(Tree parent, int columnIndex, RuleSortListener sortListener,	Map<Integer, List<Listener>> paintListeners) {
		TreeColumn tc = buildTreeColumn(parent, sortListener);
		tc.setToolTipText(tooltip());
		return tc;
	}

	public String stringValueFor(Rule rule) {
		return null;
	}

}